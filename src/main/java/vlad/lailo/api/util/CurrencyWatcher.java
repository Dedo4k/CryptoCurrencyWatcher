package vlad.lailo.api.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vlad.lailo.api.entity.Coin;
import vlad.lailo.api.entity.Note;
import vlad.lailo.api.service.CoinService;
import vlad.lailo.api.service.NoteService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class CurrencyWatcher {

    private final float THRESHOLD = -1f;
    private final NoteService noteService;
    private final CoinService coinService;
    private final String BASE_URL = "https://api.coinlore.net/api/ticker/?id=";

    public CurrencyWatcher(@Qualifier("coinServiceImpl") CoinService coinService,
                           @Qualifier("noteServiceImpl") NoteService noteService) {
        this.noteService = noteService;
        this.coinService = coinService;
    }

    @Scheduled(fixedDelayString = "PT1M")
    void watch() {
        List<Coin> coins = new ArrayList<>();
        for (Coin coin : coinService.getCoinList()) {
            RestTemplate restTemplate = new RestTemplate();
            coins.addAll(Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(BASE_URL + coin.getId(), Coin[].class))));
        }
        if (!coins.isEmpty()) {
            for (Coin coin : coins) {
                coinService.update(coin);
            }
        }
        List<Note> notes = noteService.findAll();
        for (Note note : notes) {
            Coin coin = coinService.getById(note.getCoinId());
            float diff = (coin.getPriceUsd() / note.getPrice() - 1) * 100;
            if (diff <= THRESHOLD) {
                log.warn(String.format("%s (%d,%s) %.2f -> %.2f USD, Diff: %f%%", note.getUsername(), coin.getId(),
                        coin.getSymbol(), note.getPrice(), coin.getPriceUsd(), diff));
            }
        }
    }
}

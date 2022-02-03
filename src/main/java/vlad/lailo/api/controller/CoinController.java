package vlad.lailo.api.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vlad.lailo.api.entity.Coin;
import vlad.lailo.api.service.CoinService;
import vlad.lailo.api.service.NoteService;

import java.util.List;

@RestController
@RequestMapping("/api/crypto")
public class CoinController {

    private final CoinService coinService;
    private final NoteService noteService;

    public CoinController(@Qualifier("coinServiceImpl") CoinService coinService,
                          @Qualifier("noteServiceImpl") NoteService noteService) {
        this.coinService = coinService;
        this.noteService = noteService;
    }

    @GetMapping("/coins")
    public ResponseEntity<List<Coin>> coins() {
        List<Coin> coins = coinService.getCoinList();
        return new ResponseEntity<>(coins, HttpStatus.OK);
    }

    @GetMapping("/coin")
    public ResponseEntity<Coin> coin(@RequestParam long id) {
        Coin coin = coinService.getById(id);
        return new ResponseEntity<>(coin, HttpStatus.OK);
    }

    @GetMapping("/notify")
    public ResponseEntity<HttpStatus> note(@RequestParam String username, @RequestParam long id) {
        noteService.addNote(username, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}

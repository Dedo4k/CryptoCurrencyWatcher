package vlad.lailo.api.service.impl;

import org.springframework.stereotype.Service;
import vlad.lailo.api.dao.CoinRepository;
import vlad.lailo.api.entity.Coin;
import vlad.lailo.api.service.CoinService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CoinServiceImpl implements CoinService {

    private final CoinRepository coinRepository;

    public CoinServiceImpl(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }


    @Override
    public Coin getById(long id) {
        return coinRepository.findById(id).orElseThrow(()-> new RuntimeException(String.format("Coin with id=%d not found.", id)));
    }

    @Override
    public List<Coin> getCoinList() {
        return coinRepository.findAll();
    }

    @Override
    public void update(Coin coin) {
        coinRepository.saveAndFlush(coin);
    }
}

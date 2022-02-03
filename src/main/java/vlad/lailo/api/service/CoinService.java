package vlad.lailo.api.service;

import vlad.lailo.api.entity.Coin;

import java.util.List;

public interface CoinService {

    Coin getById(long id);

    List<Coin> getCoinList();

    void update(Coin coin);
}

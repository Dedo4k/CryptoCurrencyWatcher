package vlad.lailo.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vlad.lailo.api.entity.Coin;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {
}

package vlad.lailo.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Coin implements Serializable {

    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("name")
    private String name;

    @JsonProperty("nameid")
    private String nameId;

    @JsonProperty("rank")
    @Column(name = "\"rank\"")
    private Long rank;

    @JsonProperty("price_usd")
    @Column(precision = 8, scale = 2)
    private Float priceUsd;

    @JsonProperty("percent_change_24h")
    private Float percentChangeDay;

    @JsonProperty("percent_change_1h")
    private Float percentChangeHour;

    @JsonProperty("percent_change_7d")
    private Float percentChangeWeek;

    @JsonProperty("market_cap_usd")
    private Float markerCapUsd;

    @JsonProperty("volume24")
    private Float volume;

    @JsonProperty("volume24_native")
    private Float volumeNative;

    @JsonProperty("csupply")
    private Float csupply;

    @JsonProperty("price_btc")
    private Float priceBtc;

    @JsonProperty("tsupply")
    private Float tsupply;

    @JsonProperty("msupply")
    private Float msupply;
}

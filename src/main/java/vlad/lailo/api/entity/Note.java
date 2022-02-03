package vlad.lailo.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private long coinId;

    @Column(precision = 8, scale = 2)
    private float price;

    public Note(String username, long coinId, float price) {
        this.username = username;
        this.coinId = coinId;
        this.price = price;
    }
}

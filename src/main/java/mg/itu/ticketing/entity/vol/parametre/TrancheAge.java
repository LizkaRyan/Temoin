package mg.itu.ticketing.entity.vol.parametre;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "tranche_age")
public class TrancheAge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tranche_age",insertable = false)
    private String idTrancheAge;

    @Column(name = "tranche_age")
    private String trancheAge;
}

package mg.itu.ticketing.entity.vol.parametre;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "tranche_age_promotion")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class TrancheAgePromotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tranche_age_promotion",insertable = false)
    private String idTrancheAgePromotion;

    @Column(name = "age_min")
    private int ageMin;

    @Column(name = "age_max")
    private int ageMax;

    private double pourcentage;

    @Column(name = "date_update")
    private LocalDate dateUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tranche_age")
    private TrancheAge trancheAge;
}

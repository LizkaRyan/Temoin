package mg.itu.ticketing.entity.vol.parametre;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "parametre_annulation")
public class ParametreAnnulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false,name = "id_parametre_annulation")
    private String idParametreReservation;

    @Column(name = "heure_avant_vol")
    private int heureAvantVol;

    @Column(name = "date_changement")
    private LocalDate dateChangement;

    public ParametreAnnulation(int heureAvantVol){
        this.heureAvantVol=heureAvantVol;
        this.dateChangement=LocalDate.now();
    }
}

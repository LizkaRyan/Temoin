package mg.itu.ticketing.entity.vol.parametre;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "parametre_reservation")
@Getter
@NoArgsConstructor
public class ParametreReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false,name = "id_parametre_reservation")
    private String idParametreReservation;

    @Column(name = "heure_avant_vol")
    private int heureAvantVol;

    @Column(name = "date_changement")
    private LocalDate dateChangement;

    public ParametreReservation(int heureAvantVol){
        this.heureAvantVol=heureAvantVol;
        this.dateChangement=LocalDate.now();
    }
}

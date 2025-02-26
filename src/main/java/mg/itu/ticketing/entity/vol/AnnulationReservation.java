package mg.itu.ticketing.entity.vol;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "annulation_reservation")
public class AnnulationReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_annulation_reservation")
    private String idAnnulationReservation;

    @Column(name = "date_annulation")
    private LocalDateTime dateAnnulation;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_reservation")
    private Reservation reservation;

    public AnnulationReservation(Reservation reservation){
        this.reservation=reservation;
        this.dateAnnulation=LocalDateTime.now();
    }
}

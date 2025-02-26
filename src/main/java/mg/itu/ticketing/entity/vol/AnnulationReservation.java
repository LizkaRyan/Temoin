package mg.itu.ticketing.entity.vol;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "annulation_reservation")
public class AnnulationReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_annulation_reservation")
    private String idAnnulationReservation;

    private LocalDate dateAnnulation;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_reservation")
    private Reservation reservation;
}

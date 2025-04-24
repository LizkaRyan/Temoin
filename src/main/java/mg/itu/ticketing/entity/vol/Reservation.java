package mg.itu.ticketing.entity.vol;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mg.itu.ticketing.dto.PassePortDTO;
import mg.itu.ticketing.dto.reservation.PasseportPdfData;
import mg.itu.ticketing.dto.reservation.ReservationPdfData;
import mg.itu.ticketing.entity.avion.TypeSiege;
import mg.itu.ticketing.entity.personnel.Utilisateur;

import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false,name = "id_reservation")
    private String idReservation;

    @Column(name = "date_reservation")
    private LocalDateTime dateReservation;

    @Column(name = "prix_reservation")
    private double prixReservation;

    @Column(name = "nb_passager")
    private Integer nbPassager;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_vol")
    private Vol vol;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "id_type_siege")
    private TypeSiege typeSiege;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "reservation")
    private List<AnnulationReservation> annulationReservations=new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},mappedBy = "reservation")
    private List<Passeport> passeports=new ArrayList<>();

    public void setPasseports(List<Passeport> passeports){
        this.passeports=passeports;
        this.nbPassager = passeports.size();
    }

    public ReservationPdfData getPdfData() {
        ReservationPdfData reservationPdfData = new ReservationPdfData();
        reservationPdfData.setIdReservation(idReservation);
        reservationPdfData.setDateReservation(dateReservation);
        reservationPdfData.setPrixReservation(prixReservation);
        reservationPdfData.setNbPassager(nbPassager);
        reservationPdfData.setTypeSiege(typeSiege.getTypeSiege());
        reservationPdfData.setDestination(vol.getDestination().getVille());
        reservationPdfData.setDateDepart(vol.getDateVol());
        reservationPdfData.setPseudo(this.utilisateur.getPseudo());
        List<PasseportPdfData> passeportPdfDatas = new ArrayList<>();
        for (Passeport passeport:passeports){
            PasseportPdfData passeportPdfData = new PasseportPdfData();
            passeportPdfData.setPrixReservation(passeport.getPrixReservation());
            passeportPdfData.setTrancheAge(passeport.getTrancheAge().getTrancheAge());
            passeportPdfDatas.add(passeportPdfData);
        }
        reservationPdfData.setPasseportPdfData(passeportPdfDatas);
        return reservationPdfData;
    }
}

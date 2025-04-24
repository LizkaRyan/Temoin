package mg.itu.ticketing.dto.reservation;

import lombok.Getter;
import lombok.Setter;
import mg.itu.ticketing.entity.avion.TypeSiege;
import mg.itu.ticketing.entity.vol.Vol;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ReservationPdfData {
    private String idReservation;

    private LocalDateTime dateReservation;

    private double prixReservation;

    private Integer nbPassager;

    private String destination;

    private LocalDateTime dateDepart;

    private String typeSiege;

    private String pseudo;

    private List<PasseportPdfData> passeportPdfData;

}

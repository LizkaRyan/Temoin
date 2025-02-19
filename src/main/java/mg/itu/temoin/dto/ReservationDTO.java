package mg.itu.temoin.dto;

import lombok.Getter;
import lombok.Setter;
import mg.itu.temoin.entity.avion.TypeSiege;
import mg.itu.temoin.entity.personnel.Utilisateur;
import mg.itu.temoin.entity.vol.Reservation;
import mg.itu.temoin.entity.vol.Vol;

import java.time.LocalDateTime;

@Setter
@Getter
public class ReservationDTO {
    private LocalDateTime dateReservation;
    private String idTypeSiege;
    private String idVol;

    public Reservation turnIntoReservation(Vol vol, TypeSiege typeSiege, Utilisateur utilisateur){
        Reservation reservation=new Reservation();
        reservation.setDateReservation(dateReservation);
        reservation.setVol(vol);
        reservation.setTypeSiege(typeSiege);
        reservation.setUtilisateur(utilisateur);
        return reservation;
    }
}

package mg.itu.ticketing.dto;

import lombok.Getter;
import lombok.Setter;
import mg.itu.ticketing.entity.avion.TypeSiege;
import mg.itu.ticketing.entity.personnel.Utilisateur;
import mg.itu.ticketing.entity.vol.Passeport;
import mg.itu.ticketing.entity.vol.Reservation;
import mg.itu.ticketing.entity.vol.Vol;
import mg.itu.ticketing.validation.reservation.seats.SeatsValidation;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ReservationDTO {
    private LocalDateTime dateReservation;

    private List<PassePortDTO> passePortDTOs;

    @SeatsValidation
    TypeVol typeVol=new TypeVol();

    public Reservation turnIntoReservation(Vol vol, TypeSiege typeSiege, Utilisateur utilisateur){
        Reservation reservation=new Reservation();
        reservation.setDateReservation(dateReservation);
        reservation.setVol(vol);
        reservation.setTypeSiege(typeSiege);
        reservation.setUtilisateur(utilisateur);
        return reservation;
    }

    public String getIdTypeSiege(){
        return this.typeVol.getIdTypeSiege();
    }

    public String getIdVol(){
        return this.typeVol.getIdVol();
    }
}

package mg.itu.ticketing.validation.reservation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import mg.itu.prom16.winter.validation.generic.CustomValidator;
import mg.itu.prom16.winter.validation.generic.exception.ValidationException;
import mg.itu.ticketing.dto.ReservationDTO;
import mg.itu.ticketing.entity.vol.Vol;
import mg.itu.ticketing.entity.vol.parametre.ParametreReservation;
import mg.itu.ticketing.exception.ReservationTooLateException;
import mg.itu.ticketing.repository.vol.VolRepository;
import mg.itu.ticketing.repository.vol.parametre.ParametreReservationRepo;

public class ReservationValidator extends CustomValidator<ReservationValidation,ReservationDTO> {

    ParametreReservationRepo parametreReservationRepo=new ParametreReservationRepo();

    VolRepository volRepository=new VolRepository();

    @Override
    public ValidationException validate(ReservationDTO reservationDTO, ReservationValidation reservationValidation) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();
        try{
            ParametreReservation parametreReservation = parametreReservationRepo.findActualParameter(em);
            Vol vol= volRepository.findVolById(reservationDTO.getIdVol(),em);
            if(vol.getDateVol().minusHours(parametreReservation.getHeureAvantVol()).isBefore(reservationDTO.getDateReservation())){
                return new ReservationTooLateException();
            }
        }
        finally {
            emf.close();
            em.close();
        }
        return null;
    }
}
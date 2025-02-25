package mg.itu.temoin.validation.reservation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import mg.itu.prom16.winter.validation.generic.CustomValidator;
import mg.itu.prom16.winter.validation.generic.exception.ValidationException;
import mg.itu.temoin.dto.ReservationDTO;
import mg.itu.temoin.entity.vol.Vol;
import mg.itu.temoin.entity.vol.parametre.ParametreReservation;
import mg.itu.temoin.exception.ReservationTooLateException;
import mg.itu.temoin.repository.vol.VolRepository;
import mg.itu.temoin.repository.vol.parametre.ParametreReservationRepo;

public class ReservationValidator extends CustomValidator<ReservationValidation,ReservationDTO> {

    ParametreReservationRepo parametreReservationRepo=new ParametreReservationRepo();

    VolRepository volRepository=new VolRepository();

    @Override
    public ValidationException validate(ReservationDTO reservationDTO, ReservationValidation reservationValidation) {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit"); EntityManager em = emf.createEntityManager()){
            ParametreReservation parametreReservation = parametreReservationRepo.findActualParameter(em);
            Vol vol= volRepository.findVolById(reservationDTO.getIdVol(),em);
            if(vol.getDateVol().minusHours(parametreReservation.getHeureAvantVol()).isBefore(reservationDTO.getDateReservation())){
                return new ReservationTooLateException();
            }
        }
        return null;
    }
}
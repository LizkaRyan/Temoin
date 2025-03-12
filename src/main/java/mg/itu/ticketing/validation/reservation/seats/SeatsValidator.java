package mg.itu.ticketing.validation.reservation.seats;

import mg.itu.prom16.winter.validation.generic.CustomValidator;
import mg.itu.prom16.winter.validation.generic.ValidationException;
import mg.itu.ticketing.dto.TypeVol;
import mg.itu.ticketing.exception.NoSeatsAvailableException;
import mg.itu.ticketing.repository.avion.AvionSiegeRepository;

public class SeatsValidator extends CustomValidator<SeatsValidation, TypeVol> {

    private AvionSiegeRepository avionSiegeRepository=new AvionSiegeRepository();

    @Override
    public ValidationException validate(TypeVol typeVol, SeatsValidation volValidation) {
        if(!avionSiegeRepository.isAvailable(typeVol.getIdVol(),typeVol.getIdTypeSiege())){
            return new NoSeatsAvailableException();
        }
        return null;
    }
}

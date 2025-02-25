package mg.itu.temoin.validation.reservation.seats;

import mg.itu.prom16.winter.validation.generic.CustomValidator;
import mg.itu.prom16.winter.validation.generic.exception.ValidationException;
import mg.itu.temoin.dto.TypeVol;
import mg.itu.temoin.exception.NoSeatsAvailableException;
import mg.itu.temoin.repository.avion.AvionSiegeRepository;

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

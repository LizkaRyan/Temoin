package mg.itu.temoin.validation;

import mg.itu.prom16.winter.validation.generic.CustomValidator;
import mg.itu.prom16.winter.validation.generic.exception.ValidationException;
import mg.itu.temoin.dto.TypeVol;
import mg.itu.temoin.exception.NoSeatsAvailableException;
import mg.itu.temoin.repository.avion.AvionSiegeRepository;
import mg.itu.temoin.repository.vol.ReservationRepository;
import mg.itu.temoin.repository.vol.VolRepository;

public class VolValidator extends CustomValidator<VolValidation, TypeVol> {

    private AvionSiegeRepository avionSiegeRepository=new AvionSiegeRepository();

    @Override
    public ValidationException validate(TypeVol typeVol, VolValidation volValidation) {
        if(!avionSiegeRepository.isAvailable(typeVol.getIdVol(),typeVol.getIdTypeSiege())){
            return new NoSeatsAvailableException();
        }
        return null;
    }
}

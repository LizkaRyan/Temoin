package mg.itu.ticketing.controller.vol;

import mg.itu.prom16.winter.annotation.method.Get;
import mg.itu.prom16.winter.annotation.parameter.Param;
import mg.itu.prom16.winter.annotation.type.Controller;
import mg.itu.prom16.winter.authentication.Authenticate;
import mg.itu.ticketing.authentication.ConnectedAuthentication;
import mg.itu.ticketing.exception.TooLateToCancelReservationException;
import mg.itu.ticketing.repository.vol.AnnulationReservationRepo;

@Controller(mapping = "/annulation")
@Authenticate(ConnectedAuthentication.class)
public class AnnulationController {

    AnnulationReservationRepo annulationReservationRepo=new AnnulationReservationRepo();

    @Get
    public String delete(@Param(name = "idReservation")String idReservation) throws TooLateToCancelReservationException {
        return annulationReservationRepo.save(idReservation);
    }
}

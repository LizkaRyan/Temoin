package mg.itu.temoin.controller.vol;

import mg.itu.prom16.winter.ModelAndView;
import mg.itu.prom16.winter.Session;
import mg.itu.prom16.winter.annotation.method.Get;
import mg.itu.prom16.winter.annotation.method.Post;
import mg.itu.prom16.winter.annotation.parameter.Param;
import mg.itu.prom16.winter.annotation.parameter.WinterFile;
import mg.itu.prom16.winter.annotation.type.Controller;
import mg.itu.prom16.winter.authentication.Authenticate;
import mg.itu.prom16.winter.validation.generic.annotation.IfNotValidated;
import mg.itu.temoin.authentication.ConnectedAuthentication;
import mg.itu.temoin.controller.Dispatcher;
import mg.itu.temoin.dto.ReservationDTO;
import mg.itu.temoin.repository.vol.ReservationRepository;
import mg.itu.temoin.validation.reservation.ReservationValidation;

import javax.servlet.http.Part;
import java.util.Map;

@Controller(mapping = "/reservation")
@Authenticate(ConnectedAuthentication.class)
public class ReservationController {

    Session session;
    private final ReservationRepository reservationRepository=new ReservationRepository();

    public ReservationController(Session session){
        this.session=session;
    }
    @Get("/form")
    public ModelAndView form(@Param(name = "idVol")String idVol){
        return new Dispatcher("reservation/form",session)
                .addObject("typeSieges",reservationRepository.getTypeSiegeRepository().findAll())
                .addObject("idVol",idVol);
    }

    @Post
    public String insert(@Param(name = "reservation") @ReservationValidation ReservationDTO reservation, @WinterFile(name = "passeport") Part photo) throws Exception {
        return reservationRepository.save(reservation,session,photo);
    }
}

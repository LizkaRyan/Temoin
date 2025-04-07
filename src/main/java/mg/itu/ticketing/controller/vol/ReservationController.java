package mg.itu.ticketing.controller.vol;

import mg.itu.prom16.winter.ModelAndView;
import mg.itu.prom16.winter.Session;
import mg.itu.prom16.winter.annotation.method.Get;
import mg.itu.prom16.winter.annotation.method.Post;
import mg.itu.prom16.winter.annotation.parameter.Param;
import mg.itu.prom16.winter.annotation.type.Controller;
import mg.itu.prom16.winter.authentication.Authenticate;
import mg.itu.ticketing.authentication.ConnectedAuthentication;
import mg.itu.ticketing.controller.Dispatcher;
import mg.itu.ticketing.dto.ReservationDTO;
import mg.itu.ticketing.entity.personnel.Utilisateur;
import mg.itu.ticketing.repository.vol.ReservationRepository;
import mg.itu.ticketing.validation.reservation.ReservationValidation;

import javax.servlet.http.Part;
import java.util.Map;

@Controller(mapping = "/reservation")
//@Authenticate(ConnectedAuthentication.class)
public class ReservationController {

    Session session;
    private final ReservationRepository reservationRepository=new ReservationRepository();

    public ReservationController(Session session){
        this.session=session;
    }
    @Get("/form")
    public ModelAndView form(@Param(name = "idVol")String idVol){
        return new Dispatcher("reservation/form",session)
                .addObject("tranchesAge",reservationRepository.getPassePortRepo().getTrancheAgeRepo().findAll())
                .addObject("typeSieges",reservationRepository.getTypeSiegeRepository().findAll())
                .addObject("idVol",idVol);
    }

    @Get
    public ModelAndView list(@Param(name = "message")String message){
        return new Dispatcher("reservation/index",session).addObject("reservations",reservationRepository.findReservationByIdUser(((Utilisateur)session.get("utilisateur")).getIdUtilisateur()))
                .addObject("message",message);
    }

    @Post
    public String insert(@Param(name = "reservation") @ReservationValidation ReservationDTO reservation, Map<String,Part> map) throws Exception {
        return reservationRepository.save(reservation,session);
    }
}

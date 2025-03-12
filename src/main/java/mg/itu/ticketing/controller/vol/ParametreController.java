package mg.itu.ticketing.controller.vol;

import mg.itu.prom16.winter.ModelAndView;
import mg.itu.prom16.winter.Session;
import mg.itu.prom16.winter.Validator;
import mg.itu.prom16.winter.annotation.method.Get;
import mg.itu.prom16.winter.annotation.method.Post;
import mg.itu.prom16.winter.annotation.parameter.Param;
import mg.itu.prom16.winter.annotation.type.Controller;
import mg.itu.prom16.winter.authentication.Authenticate;
import mg.itu.ticketing.authentication.AdminAuthentication;
import mg.itu.ticketing.controller.Dispatcher;
import mg.itu.ticketing.dto.Parametre;
import mg.itu.ticketing.repository.vol.parametre.ParametreReservationRepo;

import java.util.ArrayList;
import java.util.List;

@Controller(mapping = "/parametre")
@Authenticate(AdminAuthentication.class)
public class ParametreController {

    ParametreReservationRepo parametreReservationRepo=new ParametreReservationRepo();

    Session session;

    public ParametreController(Session session){
        this.session=session;
    }

    @Get("/form")
    public ModelAndView form(){
        return new Dispatcher("parametre/form",this.session).addObject("parametre",parametreReservationRepo.findAllActualParameter());
    }

    @Post
    public Object save(Validator validator, @Param(name = "parametre")Parametre parametre){
        if(!validator.isValid()){
            Dispatcher dispatcher = new Dispatcher("parametre/form",this.session);
            dispatcher.addObject("parametre",parametre);
            return validator.setError(dispatcher);
        }
        return parametreReservationRepo.saveAll(parametre);
    }
}

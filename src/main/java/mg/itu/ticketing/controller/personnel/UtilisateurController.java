package mg.itu.ticketing.controller.personnel;

import mg.itu.prom16.winter.ModelAndView;
import mg.itu.prom16.winter.Session;
import mg.itu.prom16.winter.annotation.method.Get;
import mg.itu.prom16.winter.annotation.method.Post;
import mg.itu.prom16.winter.annotation.parameter.Param;
import mg.itu.prom16.winter.annotation.type.Controller;
import mg.itu.ticketing.controller.Dispatcher;
import mg.itu.ticketing.dto.LoginDTO;
import mg.itu.ticketing.repository.personnel.UtilisateurRepository;

@Controller(mapping = "/utilisateur")
public class UtilisateurController {

    public Session session;
    private final UtilisateurRepository utilisateurRepository=new UtilisateurRepository();

    public UtilisateurController(Session session){
        this.session=session;
    }

    @Get("/login")
    public ModelAndView index(){
        return new Dispatcher("utilisateur/index",session);
    }

    @Post("/login")
    public String login(@Param(name = "utilisateur")LoginDTO loginDTO){
        return utilisateurRepository.login(this.session,loginDTO);
    }
}

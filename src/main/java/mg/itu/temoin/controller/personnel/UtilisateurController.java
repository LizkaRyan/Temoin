package mg.itu.temoin.controller.personnel;

import mg.itu.prom16.winter.ModelAndView;
import mg.itu.prom16.winter.Session;
import mg.itu.prom16.winter.annotation.method.Get;
import mg.itu.prom16.winter.annotation.method.Post;
import mg.itu.prom16.winter.annotation.parameter.Param;
import mg.itu.prom16.winter.annotation.type.Controller;
import mg.itu.temoin.controller.Dispatcher;
import mg.itu.temoin.dto.LoginDTO;
import mg.itu.temoin.repository.personnel.UtilisateurRepository;

@Controller(mapping = "/utilisateur")
public class UtilisateurController {

    private final UtilisateurRepository utilisateurRepository=new UtilisateurRepository();

    @Get("/login")
    public ModelAndView index(){
        return new Dispatcher("utilisateur/index");
    }

    @Post("/login")
    public String login(Session session, @Param(name = "utilisateur")LoginDTO loginDTO){
        return utilisateurRepository.login(session,loginDTO);
    }
}

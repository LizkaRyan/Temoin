package mg.itu.temoin.controller.personnel;

import mg.itu.prom16.winter.ModelAndView;
import mg.itu.prom16.winter.Session;
import mg.itu.prom16.winter.annotation.method.Get;
import mg.itu.prom16.winter.annotation.method.Post;
import mg.itu.prom16.winter.annotation.parameter.Param;
import mg.itu.prom16.winter.annotation.type.Controller;
import mg.itu.prom16.winter.validation.annotation.Email;
import mg.itu.temoin.controller.Dispatcher;
import mg.itu.temoin.dto.LoginDTO;
import mg.itu.temoin.repository.personnel.UtilisateurRepository;

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
//
//    @Post("/login")
//    public String login(@Param(name = "utilisateur")LoginDTO loginDTO){
//        return utilisateurRepository.login(this.session,loginDTO);
//    }

    @Post("/login")
    public String login(@Email @Param(name = "email")String email, @Param(name = "password")String password){
        LoginDTO loginDTO=new LoginDTO();
        loginDTO.setEmail(email);
        loginDTO.setPassword(password);
        return utilisateurRepository.login(this.session,loginDTO);
    }
}

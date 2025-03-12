package mg.itu.ticketing.controller.vol;

import mg.itu.prom16.winter.ModelAndView;
import mg.itu.prom16.winter.Session;
import mg.itu.prom16.winter.Validator;
import mg.itu.prom16.winter.annotation.method.Get;
import mg.itu.prom16.winter.annotation.method.Post;
import mg.itu.prom16.winter.annotation.parameter.Param;
import mg.itu.prom16.winter.annotation.type.Controller;
import mg.itu.prom16.winter.authentication.Authenticate;
import mg.itu.prom16.winter.validation.generic.annotation.IfNotValidated;
import mg.itu.ticketing.authentication.AdminAuthentication;
import mg.itu.ticketing.controller.Dispatcher;
import mg.itu.ticketing.dto.VolDTO;
import mg.itu.ticketing.dto.VolMultiCritere;
import mg.itu.ticketing.entity.personnel.Utilisateur;
import mg.itu.ticketing.repository.vol.VolRepository;

import java.util.Map;

@Controller(mapping = "/vol")
@Authenticate(AdminAuthentication.class)
public class VolController {

    private final Session session;

    private final VolRepository volRepository=new VolRepository();

    public VolController(Session session){
        this.session=session;
    }

    @Get("/form")
    public ModelAndView form(){
        return volRepository.setForm(new Dispatcher("vol/form",session));
    }

    @Get("/form/update")
    public ModelAndView form(@Param(name = "idVol")String idVol){
        return volRepository.setFormUpdate(new Dispatcher("vol/form",session),idVol);
    }

    @Get("/delete")
    public String delete(@Param(name = "idVol")String idVol){
        return volRepository.deleteById(idVol);
    }

    @Get
    @Authenticate
    public ModelAndView list(@Param(name = "vol")VolMultiCritere vol){
        Utilisateur utilisateur=((Utilisateur)session.get("utilisateur"));
        boolean isAdmin=false;
        if(utilisateur!=null){
            isAdmin=utilisateur.getRole().getRole().equals("Admin");
        }
        return new Dispatcher("vol/index",session).addObject("vols",volRepository.findByCriteria(vol))
                .addObject("villes",volRepository.getVilleRepository().findAll())
                .addObject("dateMin",vol.getDateTimeMin())
                .addObject("dateMax",vol.getDateTimeMax())
                .addObject("idVille",vol.getIdVille())
                .addObject("isAdmin",isAdmin);
    }

    @Post
    @IfNotValidated(url = "vol/form")
    public Object insert(@Param(name = "vol") VolDTO volDTO, Validator validator){
        if(!validator.isValid()){
            Dispatcher dispatcher = new Dispatcher("vol/form",this.session);
            dispatcher.addObject("vol",volDTO);
            return validator.setError(dispatcher);
        }
        return volRepository.save(volDTO);
    }
}

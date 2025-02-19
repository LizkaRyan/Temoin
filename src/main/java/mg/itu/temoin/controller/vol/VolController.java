package mg.itu.temoin.controller.vol;

import mg.itu.prom16.winter.ModelAndView;
import mg.itu.prom16.winter.Session;
import mg.itu.prom16.winter.annotation.method.Get;
import mg.itu.prom16.winter.annotation.method.Post;
import mg.itu.prom16.winter.annotation.parameter.Param;
import mg.itu.prom16.winter.annotation.type.Controller;
import mg.itu.prom16.winter.validation.generic.annotation.IfNotValidated;
import mg.itu.temoin.controller.Dispatcher;
import mg.itu.temoin.dto.VolDTO;
import mg.itu.temoin.dto.VolMultiCritere;
import mg.itu.temoin.repository.vol.VolRepository;

@Controller(mapping = "/vol")
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

    @Get
    public ModelAndView list(@Param(name = "vol")VolMultiCritere vol){
        return new Dispatcher("vol/index",session).addObject("vols",volRepository.findByCriteria(vol))
                .addObject("villes",volRepository.getVilleRepository().findAll())
                .addObject("dateMin",vol.getDateTimeMin())
                .addObject("dateMax",vol.getDateTimeMax())
                .addObject("idVille",vol.getIdVille());
    }

    @Post
    @IfNotValidated(url = "vol/form")
    public String insert(@Param(name = "vol") VolDTO volDTO){
        return volRepository.save(volDTO);
    }
}

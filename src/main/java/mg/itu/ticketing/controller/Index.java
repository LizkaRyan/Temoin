package mg.itu.ticketing.controller;

import mg.itu.prom16.winter.ModelAndView;
import mg.itu.prom16.winter.Session;
import mg.itu.prom16.winter.annotation.method.Get;
import mg.itu.prom16.winter.annotation.type.Controller;
import mg.itu.ticketing.repository.ModeleRepository;

@Controller
public class Index {

    private static final ModeleRepository modeleRepository=new ModeleRepository();

    @Get
    public ModelAndView index(Session session){
        return new Dispatcher("accueil",session);
    }
}

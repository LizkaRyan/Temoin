package mg.itu.temoin.controller;

import mg.itu.prom16.winter.ModelAndView;
import mg.itu.prom16.winter.annotation.method.Get;
import mg.itu.prom16.winter.annotation.type.Controller;
import mg.itu.temoin.repository.ModeleRepository;

@Controller
public class Index {

    private static final ModeleRepository modeleRepository=new ModeleRepository();

    @Get
    public ModelAndView index(){
        return new ModelAndView("accueil");
    }
}

package mg.itu.temoin.controller;

import mg.itu.prom16.winter.ModelAndView;
import mg.itu.prom16.winter.annotation.method.Get;
import mg.itu.prom16.winter.annotation.type.Controller;

@Controller
public class Index {
    @Get
    public ModelAndView index(){
        return new ModelAndView("accueil");
    }
}

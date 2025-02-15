package mg.itu.temoin.controller.personnel;

import mg.itu.prom16.winter.ModelAndView;
import mg.itu.prom16.winter.annotation.method.Get;
import mg.itu.prom16.winter.annotation.type.Controller;

@Controller(mapping = "/admin")
public class AdminController {
    @Get
    public ModelAndView index(){
        return new ModelAndView("index");
    }
}

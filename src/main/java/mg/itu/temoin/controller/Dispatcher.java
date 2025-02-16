package mg.itu.temoin.controller;

import mg.itu.prom16.winter.ModelAndView;

public class Dispatcher extends ModelAndView {
    public Dispatcher(String url){
        super("template");
        this.addObject("body",url+".jsp");
    }
}

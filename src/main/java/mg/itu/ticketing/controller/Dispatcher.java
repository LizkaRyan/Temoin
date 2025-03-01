package mg.itu.ticketing.controller;

import mg.itu.prom16.winter.ModelAndView;
import mg.itu.prom16.winter.Session;
import mg.itu.ticketing.entity.personnel.Utilisateur;

public class Dispatcher extends ModelAndView {
    public Dispatcher(String url, Session session){
        Utilisateur utilisateur=(Utilisateur)session.get("utilisateur");
        if(utilisateur==null){
            this.setUrl("template_utilisateur");
        }
        else if(utilisateur.getRole().getRole().equals("Membre simple")){
            this.setUrl("template_utilisateur");
        }
        else{
            this.setUrl("template_admin");
        }
        this.addObject("body",url+".jsp");
    }
}

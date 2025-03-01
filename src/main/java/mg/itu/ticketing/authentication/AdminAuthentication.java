package mg.itu.ticketing.authentication;


import mg.itu.prom16.winter.Session;
import mg.itu.prom16.winter.authentication.AuthenticationException;
import mg.itu.prom16.winter.authentication.Authenticator;
import mg.itu.ticketing.entity.personnel.Utilisateur;

public class AdminAuthentication implements Authenticator {

    private Session session;
    private String url;

    public AdminAuthentication(Session session,String url){
        this.session=session;
        this.url=url;
    }

    @Override
    public void authentificate() throws AuthenticationException {
        Utilisateur utilisateur=(Utilisateur)this.session.get("utilisateur");
        if(utilisateur==null) throw new AuthenticationException("redirect:/Ticketing/utilisateur/login?url="+url);
        else if (!utilisateur.getRole().getRole().equals("Admin"))
            throw new AuthenticationException("redirect:/Ticketing/utilisateur/login?url="+url);
    }
}

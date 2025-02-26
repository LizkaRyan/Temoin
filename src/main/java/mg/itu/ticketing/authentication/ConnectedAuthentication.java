package mg.itu.ticketing.authentication;

import mg.itu.prom16.winter.Session;
import mg.itu.prom16.winter.authentication.AuthenticationException;
import mg.itu.prom16.winter.authentication.Authenticator;

public class ConnectedAuthentication implements Authenticator {

    private final Session session;
    public ConnectedAuthentication(Session session){
        this.session=session;
    }

    @Override
    public void authentificate() throws AuthenticationException {
        if(session.get("utilisateur")==null){
            throw new AuthenticationException("redirect:/Ticketing/utilisateur/login");
        }
    }
}

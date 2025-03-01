package mg.itu.ticketing.authentication;

import mg.itu.prom16.winter.Session;
import mg.itu.prom16.winter.authentication.AuthenticationException;
import mg.itu.prom16.winter.authentication.Authenticator;

public class ConnectedAuthentication implements Authenticator {

    private final Session session;

    private final String url;
    public ConnectedAuthentication(Session session,String url){
        this.session=session;
        this.url=url;
    }

    @Override
    public void authentificate() throws AuthenticationException {
        if(session.get("utilisateur")==null){
            throw new AuthenticationException("redirect:/Ticketing/utilisateur/login?url="+url);
        }
    }
}

package mg.itu.ticketing.repository.personnel;

import jakarta.persistence.EntityManager;
import mg.itu.prom16.winter.Session;
import mg.itu.ticketing.dto.LoginDTO;
import mg.itu.ticketing.entity.personnel.Utilisateur;
import mg.itu.ticketing.repository.generic.GenericRepository;

import java.util.List;

public class UtilisateurRepository extends GenericRepository<Utilisateur,String> {
    public Utilisateur findByEmail(String email){
        return this.findOnlyOne("select u from Utilisateur u where u.email = :email",typedQuery -> {
            typedQuery.setParameter("email",email);
        }).orElseThrow(()->new RuntimeException("Email non retrouve"));
    }

    public String login(Session session, LoginDTO loginDTO,String url){
        Utilisateur utilisateur=findByEmail(loginDTO.getEmail());
        if(utilisateur.getPassword().equals(loginDTO.getPassword())){
            session.add("utilisateur",utilisateur);
            if(url!=null){
                return "redirect:/Ticketing/"+url;
            }
            return "redirect:/Ticketing";
        }
        if(url!=null){
            return "redirect:/Ticketing/utilisateur/login?url="+url;
        }
        return "redirect:/Ticketing/utilisateur/login";
    }

    public Utilisateur findUtilisiateurById(String idUtilisateur, EntityManager em) {
        return super.findById(idUtilisateur,em).orElseThrow(()->new RuntimeException("idUtilisateur non reconnue"));
    }

    public List<Utilisateur> findAllUser() {
        return this.findRequest("select u from Utilisateur u");
    }
}

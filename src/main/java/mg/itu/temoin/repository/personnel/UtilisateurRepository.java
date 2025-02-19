package mg.itu.temoin.repository.personnel;

import mg.itu.prom16.winter.Session;
import mg.itu.temoin.dto.LoginDTO;
import mg.itu.temoin.entity.personnel.Utilisateur;
import mg.itu.temoin.repository.generic.GenericRepository;

public class UtilisateurRepository extends GenericRepository<Utilisateur,String> {
    public Utilisateur findByEmail(String email){
        return this.findOnlyOne("select u from Utilisateur u where u.email = :email",typedQuery -> {
            typedQuery.setParameter("email",email);
        }).orElseThrow(()->new RuntimeException("Email non retrouve"));
    }

    public String login(Session session, LoginDTO loginDTO){
        Utilisateur utilisateur=findByEmail(loginDTO.getEmail());
        if(utilisateur.getPassword().equals(loginDTO.getPassword())){
            session.add("utilisateur",utilisateur);
            return "redirect:/Temoin";
        }
        return "redirect:/Temoin/utilisateur/login";
    }
}

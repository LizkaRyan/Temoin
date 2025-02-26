package mg.itu.ticketing.repository.vol.parametre;


import jakarta.persistence.EntityManager;
import mg.itu.ticketing.entity.vol.parametre.ParametreAnnulation;
import mg.itu.ticketing.repository.generic.GenericRepository;

public class ParametreAnnulationRepo extends GenericRepository<ParametreAnnulation,String> {

    public ParametreAnnulation findActualParameter(EntityManager em){
        return this.findOnlyOne("select p from ParametreAnnulation p where p.dateChangement in (select max(p.dateChangement) from ParametreAnnulation p)",em).orElseThrow(()->new RuntimeException("Il n'y a pas de parametre dans la base de données"));
    }
}

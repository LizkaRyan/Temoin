package mg.itu.ticketing.repository.vol.parametre;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import mg.itu.ticketing.dto.Parametre;
import mg.itu.ticketing.entity.vol.parametre.ParametreAnnulation;
import mg.itu.ticketing.entity.vol.parametre.ParametreReservation;
import mg.itu.ticketing.repository.generic.GenericRepository;

public class ParametreReservationRepo extends GenericRepository<ParametreReservation,String> {
    public ParametreReservation findActualParameter(EntityManager em){
        return this.findOnlyOne("select p from ParametreReservation p where p.dateChangement in (select max(p.dateChangement) from ParametreReservation p)",em).orElseThrow(()->new RuntimeException("Il n'y a pas de parametre dans la base de données"));
    }

    public ParametreReservation findActualParameter() {
        return this.findOnlyOne("select p from ParametreReservation p where p.dateChangement in (select max(p.dateChangement) from ParametreReservation p)").orElseThrow(() -> new RuntimeException("Il n'y a pas de parametre dans la base de données"));
    }

    public Parametre findAllActualParameter() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();
        try{
            return new Parametre(this.findActualParameter(em).getHeureAvantVol(),new ParametreAnnulationRepo().findActualParameter(em).getHeureAvantVol());
        }
        finally {
            em.close();
            emf.close();
        }
    }

    public String saveAll(Parametre parametre) {
        EntityManager em = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        try{
            em = emf.createEntityManager();
            em.getTransaction().begin();
            this.save(new ParametreReservation(parametre.getReservation()),em);
            new ParametreAnnulationRepo().save(new ParametreAnnulation(parametre.getAnnulation()),em);
            em.getTransaction().commit();
        }
        catch (Exception ex){
            if(em!=null){
                em.getTransaction().rollback();
            }
            return "redirect:/Ticketing/parametre/form?error="+ex.getMessage();
        }
        finally {
            if(em!=null){
                em.close();
            }
            emf.close();
        }
        return "redirect:/Ticketing/parametre/form";
    }
}

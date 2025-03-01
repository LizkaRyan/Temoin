package mg.itu.ticketing.repository.vol;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import mg.itu.ticketing.entity.vol.AnnulationReservation;
import mg.itu.ticketing.entity.vol.Reservation;
import mg.itu.ticketing.exception.TooLateToCancelReservationException;
import mg.itu.ticketing.repository.generic.GenericRepository;
import mg.itu.ticketing.repository.vol.parametre.ParametreAnnulationRepo;

public class AnnulationReservationRepo extends GenericRepository<AnnulationReservation,String> {

    public String save(String idReservation)throws TooLateToCancelReservationException {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try{
            emf= Persistence.createEntityManagerFactory("my-persistence-unit");
            em = emf.createEntityManager();
            Reservation reservation=new ReservationRepository().findReservationById(idReservation,em);
            new ParametreAnnulationRepo().canCancel(reservation,em);
            AnnulationReservation annulationReservation=new AnnulationReservation(reservation);
            this.save(annulationReservation,em);
            em.getTransaction().begin();
            em.persist(reservation);
            em.getTransaction().commit();
        }
        catch (Exception ex){
            return "redirect:/Ticketing/reservation?message="+ex.getMessage();
        }
        finally {
            if(emf!=null){
                emf.close();
            }
            if(em!=null){
                em.close();
            }
        }
        return "redirect:/Ticketing/reservation";
    }
}

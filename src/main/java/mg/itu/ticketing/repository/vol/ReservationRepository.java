package mg.itu.ticketing.repository.vol;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;
import mg.itu.prom16.winter.Session;
import mg.itu.ticketing.dto.ReservationDTO;
import mg.itu.ticketing.entity.personnel.Utilisateur;
import mg.itu.ticketing.entity.vol.Reservation;
import mg.itu.ticketing.repository.avion.TypeSiegeRepository;
import mg.itu.ticketing.repository.generic.GenericRepository;
import mg.itu.ticketing.repository.personnel.UtilisateurRepository;

import java.util.List;

@Getter
public class ReservationRepository extends GenericRepository<Reservation,String> {
    private TypeSiegeRepository typeSiegeRepository=new TypeSiegeRepository();
    private VolRepository volRepository=new VolRepository();
    private UtilisateurRepository utilisateurRepository=new UtilisateurRepository();
    private PassePortRepo passePortRepo=new PassePortRepo();

    public String save(ReservationDTO reservationDTO, Session session)throws Exception{
        Utilisateur utilisateur=(Utilisateur) session.get("utilisateur");
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try{
            emf= Persistence.createEntityManagerFactory("my-persistence-unit");
            em = emf.createEntityManager();
            Reservation reservation = reservationDTO.turnIntoReservation(volRepository.findVolById(reservationDTO.getIdVol(),em),typeSiegeRepository.findTypeSiegeById(reservationDTO.getIdTypeSiege(), em),utilisateurRepository.findUtilisiateurById(utilisateur.getIdUtilisateur(),em));
            reservation.setPasseports(passePortRepo.createPassePortByDTO(reservationDTO.getPassePortDTOs(),reservation,em));
            em.getTransaction().begin();
            em.persist(reservation);
            em.getTransaction().commit();
        }
        catch (Exception ex){
            throw ex;
        }
        finally {
            if(emf!=null){
                emf.close();
            }
            if(em!=null){
                em.close();
            }
        }
        return "redirect:/Ticketing/vol";
    }

    public List<Reservation> findReservationToVol(String idVol,EntityManager em){
        return this.findRequest("select r from Reservation r where r.vol.idVol = :idVol",em,typedQuery -> {
            typedQuery.setParameter("idVol",idVol);
        });
    }

    public Reservation findReservationById(String idReservation,EntityManager em){
        return super.findById(idReservation,em).orElseThrow(()->new RuntimeException("Id reservation non retrouve"));
    }

    public int getNumberSeatsUnavailable(String idVol,String idTypeSiege,EntityManager em){
        return this.findRequest("select r from Reservation r where r.vol.idVol = :idVol and r.typeSiege.idTypeSiege = :idTypeSiege",em,typedQuery -> {
            typedQuery.setParameter("idVol",idVol);
            typedQuery.setParameter("idTypeSiege",idTypeSiege);
        }).size();
    }

    public List<Reservation> findReservationByIdUser(String idUser){
        return this.findRequest("select r from Reservation r where r.utilisateur.idUtilisateur = :idUtilisateur "+
                "and not r.idReservation in (select a.reservation.idReservation from AnnulationReservation a)",
                typedQuery -> {
                    typedQuery.setParameter("idUtilisateur",idUser);
                });
    }

}

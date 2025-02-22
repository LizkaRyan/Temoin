package mg.itu.temoin.repository.vol;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;
import mg.itu.prom16.winter.Session;
import mg.itu.temoin.dto.ReservationDTO;
import mg.itu.temoin.entity.personnel.Utilisateur;
import mg.itu.temoin.entity.vol.Reservation;
import mg.itu.temoin.repository.avion.TypeSiegeRepository;
import mg.itu.temoin.repository.generic.GenericRepository;
import mg.itu.temoin.repository.personnel.UtilisateurRepository;

import javax.servlet.http.Part;

@Getter
public class ReservationRepository extends GenericRepository<Reservation,String> {
    private TypeSiegeRepository typeSiegeRepository=new TypeSiegeRepository();
    private VolRepository volRepository=new VolRepository();
    private UtilisateurRepository utilisateurRepository=new UtilisateurRepository();

    public String save(ReservationDTO reservationDTO, Session session, Part part)throws Exception{
        Utilisateur utilisateur=(Utilisateur) session.get("utilisateur");
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try{
            emf= Persistence.createEntityManagerFactory("my-persistence-unit");
            em = emf.createEntityManager();
            Reservation reservation = reservationDTO.turnIntoReservation(volRepository.findVolById(reservationDTO.getIdVol(),em),typeSiegeRepository.findTypeSiegeById(reservationDTO.getIdTypeSiege(), em),utilisateurRepository.findUtilisiateurById(utilisateur.getIdUtilisateur(),em));
            reservation=setPrixReservation(reservation);
            reservation.setPhotoSrc(part);
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
        return "redirect:/Temoin/vol";
    }

    public Reservation setPrixReservation(Reservation reservation) {
        double prix = reservation.getVol().getPrixVol();
        prix += reservation.getTypeSiege().getPrixSiege();
        reservation.setPrixReservation(prix);
        return reservation;
    }

    public int getNumberSeatsUnavailable(String idVol,String idTypeSiege,EntityManager em){
        return this.findRequest("select r from Reservation r where r.vol.idVol = :idVol and r.typeSiege.idTypeSiege = :idTypeSiege",em,typedQuery -> {
            typedQuery.setParameter("idVol",idVol);
            typedQuery.setParameter("idTypeSiege",idTypeSiege);
        }).size();
    }
}

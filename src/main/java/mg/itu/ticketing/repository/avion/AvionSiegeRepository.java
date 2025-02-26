package mg.itu.ticketing.repository.avion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import mg.itu.ticketing.entity.avion.AvionSiege;
import mg.itu.ticketing.entity.id.AvionSiegeId;
import mg.itu.ticketing.entity.vol.Vol;
import mg.itu.ticketing.repository.generic.GenericRepository;
import mg.itu.ticketing.repository.vol.ReservationRepository;
import mg.itu.ticketing.repository.vol.VolRepository;

import java.util.Optional;

public class AvionSiegeRepository extends GenericRepository<AvionSiege, AvionSiegeId> {

    private VolRepository volRepository=new VolRepository();

    private ReservationRepository reservationRepository= new ReservationRepository();

    private AvionSiege getAvionSiege(String idAvion,String idTypeSiege,EntityManager em){
        Optional<AvionSiege> avionSiege=this.findOnlyOne(
                "select a from AvionSiege a where a.avionSiegeId.idTypeSiege = :idTypeSiege and a.avionSiegeId.idAvion = :idAvion",
                typedQuery -> {
                    typedQuery.setParameter("idTypeSiege",idTypeSiege);
                    typedQuery.setParameter("idAvion",idAvion);
                },em
                );
        return avionSiege.orElse(new AvionSiege());
    }

    public int getNombreSiegeVol(String idVol,String idTypeSiege,EntityManager em){
            Vol vol=this.volRepository.findVolById(idVol,em);
            return this.getAvionSiege(vol.getAvion().getIdAvion(),idTypeSiege,em).getNombre();
    }

    public boolean isAvailable(String idVol,String idTypeSiege){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();
        try{
            int nombreSiege=this.getNombreSiegeVol(idVol,idTypeSiege,em);
            int nombreSiegePrise=this.reservationRepository.getNumberSeatsUnavailable(idVol,idTypeSiege,em);
            return nombreSiegePrise<nombreSiege;
        }
        finally {
            em.close();
            emf.close();
        }
    }
}

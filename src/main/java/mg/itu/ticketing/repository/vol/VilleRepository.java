package mg.itu.ticketing.repository.vol;

import jakarta.persistence.EntityManager;
import mg.itu.ticketing.entity.vol.Ville;
import mg.itu.ticketing.repository.generic.GenericRepository;

import java.util.List;

public class VilleRepository extends GenericRepository<Ville,String> {
    public List<Ville> findAll(){
        return this.findRequest("select v from Ville v");
    }

    public List<Ville> findAll(EntityManager em){
        return this.findRequest("select v from Ville v",em);
    }

    public Ville findVilleById(String id, EntityManager em){
        return this.findById(id,em).orElseThrow(()->new RuntimeException("Id ville non reconnue"));
    }
}

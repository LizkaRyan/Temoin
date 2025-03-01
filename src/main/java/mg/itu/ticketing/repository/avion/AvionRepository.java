package mg.itu.ticketing.repository.avion;

import jakarta.persistence.EntityManager;
import mg.itu.ticketing.entity.avion.Avion;
import mg.itu.ticketing.repository.generic.GenericRepository;

import java.util.List;

public class AvionRepository extends GenericRepository<Avion,String> {
    public List<Avion> findAll(){
        return this.findRequest("select a from Avion a");
    }

    public List<Avion> findAll(EntityManager em){
        return this.findRequest("select a from Avion a",em);
    }

    public Avion findAvionById(String idAvion, EntityManager em) {
        return this.findById(idAvion,em).orElseThrow(()->new RuntimeException("Id avion non reconnue"));
    }
}

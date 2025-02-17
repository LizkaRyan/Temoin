package mg.itu.temoin.repository.avion;

import jakarta.persistence.EntityManager;
import mg.itu.temoin.entity.avion.Avion;
import mg.itu.temoin.repository.generic.GenericRepository;

import java.util.List;

public class AvionRepository extends GenericRepository<Avion,String> {
    public AvionRepository() {
        super(Avion.class);
    }

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

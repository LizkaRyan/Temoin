package mg.itu.ticketing.repository.avion;

import jakarta.persistence.EntityManager;
import mg.itu.ticketing.entity.avion.TypeSiege;
import mg.itu.ticketing.repository.generic.GenericRepository;

import java.util.List;

public class TypeSiegeRepository extends GenericRepository<TypeSiege,String> {
    public List<TypeSiege> findAll(){
        return this.findRequest("select t from TypeSiege t");
    }

    public TypeSiege findTypeSiegeById(String id, EntityManager em){
        return super.findById(id,em).orElseThrow(()->new RuntimeException("Id Type siege non reconnue"));
    }
}

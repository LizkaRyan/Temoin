package mg.itu.ticketing.repository.vol.parametre;

import jakarta.persistence.EntityManager;
import mg.itu.ticketing.entity.vol.Vol;
import mg.itu.ticketing.entity.vol.parametre.TrancheAge;
import mg.itu.ticketing.repository.generic.GenericRepository;

import java.util.List;

public class TrancheAgeRepo extends GenericRepository<TrancheAge,String> {

    public TrancheAge findTrancheById(String idTranche, EntityManager em){
        return super.findById(idTranche,em).orElseThrow(()->new RuntimeException("Id Vol non reconnue"));
    }

    public List<TrancheAge> findAll(){
        return super.findRequest("select t from TrancheAge t");
    }
}

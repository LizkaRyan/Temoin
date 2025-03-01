package mg.itu.ticketing.repository.vol;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;
import mg.itu.prom16.winter.ModelAndView;
import mg.itu.ticketing.dto.VolDTO;
import mg.itu.ticketing.dto.VolMultiCritere;
import mg.itu.ticketing.entity.vol.Vol;
import mg.itu.ticketing.repository.avion.AvionRepository;
import mg.itu.ticketing.repository.generic.GenericRepository;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class VolRepository extends GenericRepository<Vol,String> {
    private final VilleRepository villeRepository=new VilleRepository();

    private final AvionRepository avionRepository=new AvionRepository();

    public String save(VolDTO volDTO){
        EntityManagerFactory emf=null;
        EntityManager em=null;
        try {
            emf = Persistence.createEntityManagerFactory("my-persistence-unit");
            em = emf.createEntityManager();
            // Commencer la transaction
            em.getTransaction().begin();
            Vol vol = volDTO.convertIntoDto(villeRepository.findVilleById(volDTO.getIdVille(), em), avionRepository.findAvionById(volDTO.getIdAvion(), em));
            if(vol.getIdVol()==null){
                this.save(vol, em);
            }
            else{
                em.merge(vol);
            }
            em.getTransaction().commit();
        }
        catch (Exception ex){
            throw ex;
        }
        finally {
            if(em!=null){
                em.close();
            }
            if(emf!=null){
                emf.close();
            }
        }
        return "redirect:/Ticketing/vol";
    }

    public ModelAndView setForm(ModelAndView modelAndView){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();
        try {
            return modelAndView.addObject("villes",villeRepository.findAll(em))
                    .addObject("avions",avionRepository.findAll(em));
        }
        finally {
            em.close();
            emf.close();
        }
    }

    public List<Vol> findAll(){
        return this.findRequest("select v from Vol v");
    }

    public List<Vol> findByCriteria(VolMultiCritere critere){
        if(critere.getDateTimeMax()==null){
            critere.setDateTimeMax(LocalDateTime.of(2077,1,1,0,0));
        }
        if(critere.getDateTimeMin()==null){
            critere.setDateTimeMin(LocalDateTime.of(1980,1,1,0,0));
        }
        if(critere.getIdVille()==null){
            critere.setIdVille("Tous");
        }
        return this.findRequest("select v from Vol v where :dateMin <= v.dateVol and v.dateVol <= :dateMax and (v.destination.idVille = :idVille or :idVille = 'Tous')",typedQuery -> {
            typedQuery.setParameter("dateMin",critere.getDateTimeMin());
            typedQuery.setParameter("dateMax",critere.getDateTimeMax());
            typedQuery.setParameter("idVille",critere.getIdVille());
        });
    }


    public Vol findVolById(String idVol,EntityManager em){
        return super.findById(idVol,em).orElseThrow(()->new RuntimeException("Id Vol non reconnue"));
    }

    public ModelAndView setFormUpdate(ModelAndView modelAndView,String idVol) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();
        try {
            return modelAndView.addObject("villes",villeRepository.findAll(em))
                    .addObject("avions",avionRepository.findAll(em))
                    .addObject("vol",findVolById(idVol,em).turnIntoDTO());
        }
        finally {
            em.close();
            emf.close();
        }
    }

    public String deleteById(String idVol){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();
        try{
            Vol vol=this.findVolById(idVol,em);
            em.getTransaction().begin();
            this.delete(vol,em);
            em.getTransaction().commit();
            return "redirect:/Ticketing/vol";
        }
        catch (Exception ex){
            em.getTransaction().rollback();
            throw ex;
        }
        finally {
            em.close();
            emf.close();
        }
    }
}

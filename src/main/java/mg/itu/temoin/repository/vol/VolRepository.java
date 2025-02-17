package mg.itu.temoin.repository.vol;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;
import mg.itu.prom16.winter.ModelAndView;
import mg.itu.temoin.dto.VolDTO;
import mg.itu.temoin.entity.vol.Vol;
import mg.itu.temoin.repository.avion.AvionRepository;
import mg.itu.temoin.repository.generic.GenericRepository;

import java.util.List;

@Getter
public class VolRepository extends GenericRepository<Vol,String> {
    public VolRepository() {
        super(Vol.class);
    }

    private final VilleRepository villeRepository=new VilleRepository();

    private final AvionRepository avionRepository=new AvionRepository();

    public String save(VolDTO volDTO){
        EntityManager em=null;
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit")) {
            em = emf.createEntityManager();
            // Commencer la transaction
            em.getTransaction().begin();
            Vol vol = volDTO.convertIntoDto(villeRepository.findVilleById(volDTO.getIdVille(), em), avionRepository.findAvionById(volDTO.getIdAvion(), em));
            this.save(vol, em);
            em.getTransaction().commit();
        }
        catch (Exception ex){
            if(em!=null){
                em.getTransaction().rollback();
            }
        }
        finally {
            assert em != null;
            em.close();
        }
        return "redirect:/Temoin/vol/form";
    }

    public ModelAndView setForm(ModelAndView modelAndView){
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit"); EntityManager em = emf.createEntityManager();) {
            return modelAndView.addObject("villes",villeRepository.findAll(em))
                    .addObject("avions",avionRepository.findAll(em));
        }
    }

    public List<Vol> findAll(){
        return this.findRequest("select v from Vol v");
    }
}

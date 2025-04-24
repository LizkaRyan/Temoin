package mg.itu.ticketing.entity.vol;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mg.itu.ticketing.dto.VolDTO;
import mg.itu.ticketing.entity.avion.Avion;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Vol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false,name = "id_vol")
    private String idVol;

    @Column(name = "date_vol")
    private LocalDateTime dateVol;

    @Column(name = "prix_vol")
    private double prixVol;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "destination",referencedColumnName = "id_ville")
    private Ville destination;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "id_avion")
    private Avion avion;

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL},mappedBy = "vol")
    private List<Reservation> reservations;

    public VolDTO turnIntoDTO(){
        VolDTO volDTO=new VolDTO();
        volDTO.setIdVol(this.idVol);
        volDTO.setDateVol(this.dateVol);
        volDTO.setPrixVol(this.prixVol);
        volDTO.setIdVille(this.destination.getIdVille());
        volDTO.setIdAvion(this.getAvion().getIdAvion());
        return volDTO;
    }
}

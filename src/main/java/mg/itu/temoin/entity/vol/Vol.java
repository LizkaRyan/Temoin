package mg.itu.temoin.entity.vol;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mg.itu.temoin.entity.avion.Avion;

import java.time.LocalDateTime;

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

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "destination",referencedColumnName = "id_ville")
    private Ville destination;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_avion")
    private Avion avion;
}

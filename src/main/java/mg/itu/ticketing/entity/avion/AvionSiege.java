package mg.itu.ticketing.entity.avion;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mg.itu.ticketing.entity.id.AvionSiegeId;

@Entity
@Table(name = "avion_siege")
@Getter
@Setter
public class AvionSiege {
    @EmbeddedId
    private AvionSiegeId avionSiegeId;


    @MapsId("idAvion")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_avion",insertable = false,updatable = false)
    private Avion avion;

    @MapsId("idTypeSiege")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_siege",insertable = false,updatable = false)
    private TypeSiege typeSiege;

    private int nombre=0;
}

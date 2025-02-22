package mg.itu.temoin.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
public class AvionSiegeId implements Serializable {
    @Column(name = "id_avion")
    private String idAvion;
    @Column(name = "id_type_siege")
    private String idTypeSiege;
}

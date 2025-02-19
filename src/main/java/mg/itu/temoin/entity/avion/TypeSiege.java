package mg.itu.temoin.entity.avion;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TypeSiege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false,name = "id_type_siege")
    private String idTypeSiege;

    @Column(name = "type_siege")
    private String typeSiege;
}

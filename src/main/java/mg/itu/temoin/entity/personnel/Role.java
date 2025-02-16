package mg.itu.temoin.entity.personnel;

import jakarta.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false,name = "id_role")
    private String idRole;

    public String role;
}

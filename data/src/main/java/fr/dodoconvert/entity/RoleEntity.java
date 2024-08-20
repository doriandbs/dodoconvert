package fr.dodoconvert.entity;


import fr.dodoconvert.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@Table(name="roles")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public RoleEntity(RoleName roleName) {
        this.roleName = roleName;
    }

}

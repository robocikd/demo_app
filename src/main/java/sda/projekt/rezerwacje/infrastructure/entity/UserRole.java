package sda.projekt.rezerwacje.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sda.projekt.rezerwacje.infrastructure.dto.UserRoleDto;

import javax.persistence.*;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true, length = 100)
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public UserRoleDto toDto(UserRole userRole) {
        return UserRoleDto.builder()
                .id(id)
                .role(role)
                .users(users)
                .build();
    }
}

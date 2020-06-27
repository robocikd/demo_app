package sda.projekt.rezerwacje.infrastructure.dto;

import lombok.*;
import sda.projekt.rezerwacje.infrastructure.entity.User;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoleDto {

    private Long id;
    private String role;
    private Set<User> users;

}

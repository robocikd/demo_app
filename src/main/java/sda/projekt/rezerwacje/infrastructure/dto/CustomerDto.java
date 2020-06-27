package sda.projekt.rezerwacje.infrastructure.dto;

import lombok.*;
import sda.projekt.rezerwacje.infrastructure.entity.Reservation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private Long id;
    @NotEmpty
    private String login;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String customerName;
    @NotEmpty
    private String customerSurname;
    @NotEmpty
    private String customerAddress;

}

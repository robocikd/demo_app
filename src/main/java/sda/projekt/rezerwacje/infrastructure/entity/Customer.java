package sda.projekt.rezerwacje.infrastructure.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Column(name = "customer_first_name")
    @NotEmpty
    private String customerName;

    @Column(name = "customer_surname")
    @NotEmpty
    private String customerSurname;

    @Column(name = "customer_address")
    @NotEmpty
    private String customerAddress;

    @OneToMany(mappedBy = "customer")
    private Set<Reservation> reservations;

}

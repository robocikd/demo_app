package sda.projekt.rezerwacje.infrastructure.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    @NotEmpty
    private String login;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    @Email
    private String email;

    @NotEmpty
    private String password;

    @Transient
    private String passwordConfirm;

    @Column(name = "enable")
    private boolean enable;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRole> roles;

}

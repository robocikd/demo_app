package sda.projekt.rezerwacje.infrastructure.entity;

import javax.persistence.*;

@Entity//(name = "admin")
@Table(name = "admins")
public class Admin extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "employed_since")
    private String employedSince;

}

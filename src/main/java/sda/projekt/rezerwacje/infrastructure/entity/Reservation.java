package sda.projekt.rezerwacje.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "reservation_from")
    private LocalDate from;
    @Column(name = "reservation_to")
    private LocalDate to;

    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Cottage cottage;

}

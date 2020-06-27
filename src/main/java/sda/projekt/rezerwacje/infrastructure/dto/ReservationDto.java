package sda.projekt.rezerwacje.infrastructure.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import sda.projekt.rezerwacje.infrastructure.entity.Cottage;
import sda.projekt.rezerwacje.infrastructure.entity.Customer;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDto {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate from;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate to;

    private Cottage cottage;
    private Customer customer;
}

package sda.projekt.rezerwacje.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.projekt.rezerwacje.infrastructure.dto.ReservationDto;
import sda.projekt.rezerwacje.infrastructure.entity.Cottage;
import sda.projekt.rezerwacje.infrastructure.entity.Customer;
import sda.projekt.rezerwacje.infrastructure.entity.Reservation;

import java.util.List;
import java.util.Set;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByCustomer(Customer customer);

    Set<Reservation> findAllByCottage_Id(Long cottageId);


}

package sda.projekt.rezerwacje.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sda.projekt.rezerwacje.infrastructure.dto.CottageDto;
import sda.projekt.rezerwacje.infrastructure.dto.ReservationDto;
import sda.projekt.rezerwacje.infrastructure.entity.Customer;
import sda.projekt.rezerwacje.infrastructure.entity.Reservation;
import sda.projekt.rezerwacje.infrastructure.mapper.ReservationMapper;
import sda.projekt.rezerwacje.infrastructure.repository.ReservationRepository;

import javax.management.openmbean.OpenDataException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public void createReservation(ReservationDto reservationDto, CottageDto cottageDto) {
        reservationRepository.save(reservationMapper.toEntity(reservationDto, cottageDto));
    }

    public List<ReservationDto> findAllByCustomer(Customer customer) {
        return reservationRepository.findAllByCustomer(customer)
                .stream().map(it -> reservationMapper.toDto(it)).collect(Collectors.toList());
    }

    public boolean canBeBooked(ReservationDto reservationDto, CottageDto cottageDto) throws IllegalStateException {
        LocalDate dateFrom = reservationDto.getFrom();
        LocalDate dateTo = reservationDto.getTo();
        LocalDate dateNow = LocalDate.now();
        String message = null;
        if (dateTo.isBefore(dateFrom)) {
            message = "Data przyjazdu nie może być późniejsza niż data wyjazdu";
            throw new IllegalStateException(message);
        }
        if (dateNow.isAfter(dateFrom)) {
            message = "Data przyjazdu nie może być wcześniejsza niż dzisiaj";
            throw new IllegalStateException(message);
        }
        if (cottageDto.getReservations() != null && cottageDto.getReservations().size() > 0) {
            for (Reservation res : cottageDto.getReservations()) {
                if (dateTo.isAfter(res.getFrom()) && dateFrom.isBefore(res.getTo())) {
                    message = "Niestety wybrany termin jest zajęty";
                    throw new IllegalStateException(message);
                }
            }
        }
        return true;
    }
}

package sda.projekt.rezerwacje.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sda.projekt.rezerwacje.domain.CottageService;
import sda.projekt.rezerwacje.domain.CustomerService;
import sda.projekt.rezerwacje.infrastructure.dto.CottageDto;
import sda.projekt.rezerwacje.infrastructure.dto.ReservationDto;
import sda.projekt.rezerwacje.infrastructure.entity.Reservation;

@Component
@RequiredArgsConstructor
public class ReservationMapper {

    private final CustomerService customerService;
    private final CottageService cottageService;

    public Reservation toEntity(ReservationDto reservationDto, CottageDto cottageDto) {
        Reservation reservation = new Reservation();
        reservation.setFrom(reservationDto.getFrom());
        reservation.setTo(reservationDto.getTo());
        reservation.setCustomer(customerService.findCustomerByLogin(customerService.getLoggedUserLogin()));
        reservation.setCottage(CottageMapper.toEntity(cottageDto));
        return reservation;
    }


    public ReservationDto toDto(Reservation res){
        ReservationDto resDto = new ReservationDto();
        resDto.setId(res.getId());
        resDto.setFrom(res.getFrom());
        resDto.setTo(res.getTo());
        resDto.setCottage(res.getCottage());
        resDto.setCustomer(res.getCustomer());
        return resDto;
    }

}

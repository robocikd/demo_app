package sda.projekt.rezerwacje.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import sda.projekt.rezerwacje.domain.CottageFilesService;
import sda.projekt.rezerwacje.infrastructure.dto.CottageDto;
import sda.projekt.rezerwacje.infrastructure.entity.Cottage;
import sda.projekt.rezerwacje.infrastructure.repository.ReservationRepository;

@Component
@RequiredArgsConstructor
public class CottageMapper {

    private final CottageFilesService cottageFilesService;
    private final ReservationRepository reservationRepository;

    public static Cottage toEntity(CottageDto dto) {
        return Cottage.builder()
                .id(dto.getId())
                .name(dto.getName())
                .region(dto.getRegion())
                .city(dto.getCity())
                .streetAndNumber(dto.getStreetAndNumber())
                .noOfRooms(dto.getNoOfRooms())
                .build();
    }

    public CottageDto toDto(Cottage cottage) {

        CottageDto cottageDto = new CottageDto();

        cottageDto.setId(cottage.getId());
        cottageDto.setName(cottage.getName());
        cottageDto.setRegion(cottage.getRegion());
        cottageDto.setCity(cottage.getCity());
        cottageDto.setStreetAndNumber(cottage.getStreetAndNumber());
        cottageDto.setNoOfRooms(cottage.getNoOfRooms());
        cottageDto.setReservations(reservationRepository.findAllByCottage_Id(cottage.getId()));
        cottageDto.setCottageFiles(cottageFilesService.findCottageFilesByCottageId(cottage.getId()));
        return cottageDto;
    }

}

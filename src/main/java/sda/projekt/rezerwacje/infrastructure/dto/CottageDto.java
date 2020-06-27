package sda.projekt.rezerwacje.infrastructure.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import sda.projekt.rezerwacje.infrastructure.entity.CottageFiles;
import sda.projekt.rezerwacje.infrastructure.entity.Reservation;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CottageDto {
    private Long id;
    private String name;
    private String region;
    private String city;
    private String streetAndNumber;
    private Integer noOfRooms;

    private Set<Reservation> reservations;

    private List<CottageFiles> cottageFiles;

    private List<MultipartFile> files;



}

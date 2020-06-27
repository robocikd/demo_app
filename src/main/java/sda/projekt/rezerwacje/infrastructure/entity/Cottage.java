package sda.projekt.rezerwacje.infrastructure.entity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cottage")
public class Cottage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "cottage_name")
    private String name;
    @Column(name = "cottage_region")
    private String region;
    @Column(name = "cottage_city")
    private String city;
    @Column(name = "cottage_street_and_number")
    private String streetAndNumber;
    @Column(name = "cottage_no_of_rooms")
    private Integer noOfRooms;

    @OneToMany(mappedBy = "cottage", cascade = CascadeType.REMOVE)
    private Set<Reservation> reservations;

    @OneToMany(mappedBy = "cottage", cascade = CascadeType.REMOVE)
    private Set<CottageFiles> cottageFiles;

    @Transient
    private List<MultipartFile> files = new ArrayList<>();
    @Transient
    private List<String> removeImages = new ArrayList<>();

}

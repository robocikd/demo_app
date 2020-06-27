package sda.projekt.rezerwacje.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cottage_files")
public class CottageFiles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "modified_file_name")
    private String modifiedFileName;
    @Column(name = "file_extension")
    private String fileExtension;

    @ManyToOne
    @JoinColumn(name = "cottage_id")
    private Cottage cottage;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CottageFiles{");
        sb.append("id=").append(id);
        sb.append(", fileName='").append(fileName).append('\'');
        sb.append(", modifiedFileName='").append(modifiedFileName).append('\'');
        sb.append(", fileExtension='").append(fileExtension).append('\'');
        sb.append(", cottage=").append(cottage);
        sb.append('}');
        return sb.toString();
    }
}

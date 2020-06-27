package sda.projekt.rezerwacje.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.projekt.rezerwacje.infrastructure.entity.Cottage;

import java.util.List;

@Repository
public interface CottageRepository extends JpaRepository<Cottage, Long> {

    List<Cottage> findByRegion(String region);


}

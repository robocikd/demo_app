package sda.projekt.rezerwacje.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sda.projekt.rezerwacje.infrastructure.entity.CottageFiles;

import java.util.List;
import java.util.Set;

@Repository
public interface CottageFilesRepository extends JpaRepository<CottageFiles, Long> {

    @Query(value = "SELECT * FROM cottage_files where cottage_id = :cottageId", nativeQuery = true)
    List<CottageFiles> findCottageFilesByCottageId(@Param("cottageId") Long cottageId);

    @Query(value = "SELECT * FROM cottage_files where cottage_id = :cottageId LIMIT 1", nativeQuery = true)
    List<CottageFiles> findFirstCottageFileByCottageId(@Param("cottageId") Long cottageId);
}

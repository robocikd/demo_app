package sda.projekt.rezerwacje.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sda.projekt.rezerwacje.infrastructure.entity.CottageFiles;
import sda.projekt.rezerwacje.infrastructure.repository.CottageFilesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CottageFilesService {

    private final CottageFilesRepository cottageFilesRepository;

    public List<CottageFiles> findCottageFilesByCottageId(Long cottageId) {
        return cottageFilesRepository.findCottageFilesByCottageId(cottageId);
    }

    public List<CottageFiles> findFirstCottageFileByCottageId(Long cottageId) {
        return cottageFilesRepository.findFirstCottageFileByCottageId(cottageId);
    }

}

package sda.projekt.rezerwacje.domain;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sda.projekt.rezerwacje.infrastructure.dto.CottageDto;
import sda.projekt.rezerwacje.infrastructure.entity.Cottage;
import sda.projekt.rezerwacje.infrastructure.entity.CottageFiles;
import sda.projekt.rezerwacje.infrastructure.mapper.CottageMapper;
import sda.projekt.rezerwacje.infrastructure.repository.CottageFilesRepository;
import sda.projekt.rezerwacje.infrastructure.repository.CottageRepository;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CottageService {

    private final CottageRepository cottageRepository;
    private final UploadPathService uploadPathService;
    private final CottageFilesRepository cottageFilesRepository;
    private final CottageMapper cottageMapper;

    public List<CottageDto> findAll() {
        return cottageRepository.findAll().stream().map(it -> cottageMapper.toDto(it)).collect(Collectors.toList());
    }

    public List<CottageDto> findAllByRegion(String region) {
        return cottageRepository.findByRegion(region)
                .stream().map(it -> cottageMapper.toDto(it)).collect(Collectors.toList());
    }

    public CottageDto findById(Long id) {
        return cottageRepository.findById(id)
                .map(it -> cottageMapper.toDto(it))
                .orElseThrow(() -> new IllegalStateException("Nie istnieje taki domek"));
    }

    public Cottage createOrUpdate(CottageDto dto) {
        Cottage dbCottage = cottageRepository.save(CottageMapper.toEntity(dto));
        if (dbCottage != null && dto.getFiles() != null && dto.getFiles().size() > 0 && dto.getFiles().get(0).getSize() > 0) {
            for (MultipartFile multipartFile : dto.getFiles()) {
                String originalFilename = multipartFile.getOriginalFilename();
                String modifiedFileName = FilenameUtils.getBaseName(originalFilename) + "_"
                        + System.currentTimeMillis() + "." + FilenameUtils.getExtension(originalFilename);
                File storeFile = uploadPathService.getFilePath(modifiedFileName, "images");
                System.out.println(storeFile);
                if (storeFile != null) {
                    try {
                        FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                CottageFiles files = new CottageFiles();
                files.setFileExtension(FilenameUtils.getExtension(originalFilename));
                files.setFileName(originalFilename);
                files.setModifiedFileName(modifiedFileName);
                files.setCottage(dbCottage);
                cottageFilesRepository.save(files);
            }
        }
        return dbCottage;
    }

    public void delete(Long cottageId) {
        List<CottageFiles> cottageFilesByCottageId = cottageFilesRepository.findCottageFilesByCottageId(cottageId);
        if (cottageFilesByCottageId != null) {
            for (CottageFiles cottageFile : cottageFilesByCottageId) {
                File fileToDelete = uploadPathService.getFilePath(cottageFile.getModifiedFileName(), "images");
                boolean success = fileToDelete.delete();
            }
            cottageRepository.deleteById(cottageId);
        }
    }

}

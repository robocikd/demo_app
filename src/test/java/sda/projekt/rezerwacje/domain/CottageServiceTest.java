package sda.projekt.rezerwacje.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sda.projekt.rezerwacje.infrastructure.dto.CottageDto;
import sda.projekt.rezerwacje.infrastructure.entity.Cottage;
import sda.projekt.rezerwacje.infrastructure.mapper.CottageMapper;
import sda.projekt.rezerwacje.infrastructure.repository.CottageRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CottageServiceTest {

    @InjectMocks
    CottageService cottageService;
    @Mock
    CottageRepository cottageRepository;
    @Mock
    CottageMapper cottageMapper;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findById() {

        Cottage cottage = new Cottage();
        cottage.setId(1L);
        cottage.setCity("Lublin");
        cottage.setRegion("morze");

        CottageDto cottageDto = new CottageDto();
        cottageDto.setId(1L);

        when(cottageMapper.toDto(cottage)).thenReturn(cottageDto);
        when(cottageRepository.findById(anyLong())).thenReturn(Optional.of(cottage));

        CottageDto byId = cottageService.findById(1L);
        assertNotNull(byId);
    }
}

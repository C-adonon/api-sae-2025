package future.SAE.application.mapping;


import java.util.List;

import future.SAE.api.dto.SectionReponseDTO;
import future.SAE.api.dto.SectionRequeteDTO;
import future.SAE.infrastructure.mapping.CoursMapper;
import future.SAE.infrastructure.mapping.FichierMapper;
import org.mapstruct.Mapper;

import future.SAE.domain.model.Section;
import future.SAE.infrastructure.persistence.SectionJPA;

@Mapper(componentModel = "spring", uses = { CoursMapper.class, FichierMapper.class })
public interface SectionDTOMapper {

     Section toDomain(SectionRequeteDTO sectionRequeteDTO);

     SectionReponseDTO toDTO(Section section);

     List<Section> toDomainList(List<SectionRequeteDTO> sectionRequeteDTOList);

     List<SectionReponseDTO> toDTOList(List<Section> sectionList);
}

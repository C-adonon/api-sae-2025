package future.SAE.api.mapping;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import future.SAE.api.dto.SectionReponseDTO;
import future.SAE.domain.model.Section;

@Mapper(componentModel = "spring", uses = { FichierDTOMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SectionDTOMapper {

    @Mapping(target = "id", source = "idSection")
    @Mapping(target = "coursId", source = "cours.idCours")
    SectionReponseDTO toDTO(Section section);

    List<SectionReponseDTO> toDTOList(List<Section> sections);
}

package future.SAE.api.mapping;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import future.SAE.api.dto.FichierReponseDTO;
import future.SAE.domain.model.Fichier;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FichierDTOMapper {

    @Mapping(target = "sectionId", source = "section.idSection")
    FichierReponseDTO toDTO(Fichier fichier);

    List<FichierReponseDTO> toDTOList(List<Fichier> fichiers);

    default LocalDateTime map(Timestamp value) {
        return value == null ? null : value.toLocalDateTime();
    }
}

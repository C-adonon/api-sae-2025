package future.SAE.api.mapping;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import future.SAE.api.dto.CoursReponseDTO;
import future.SAE.domain.model.Cours;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CoursDTOMapper {

    @Mapping(target = "id", source = "idCours")
    @Mapping(target = "professeurId", source = "professeur.id")
    @Mapping(target = "formationId", source = "formation.idFormation")
    @Mapping(target = "section", ignore = true)
    CoursReponseDTO toDTO(Cours cours);

    List<CoursReponseDTO> toDTOList(List<Cours> coursList);

    default LocalDateTime map(Timestamp value) {
        return value == null ? null : value.toLocalDateTime();
    }
}

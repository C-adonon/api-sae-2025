package future.SAE.api.mapping;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import future.SAE.api.dto.FormationReponseDTO;
import future.SAE.domain.model.Formation;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FormationDTOMapper {

    @Mapping(target = "id", source = "idFormation")
    FormationReponseDTO toDTO(Formation formation);

    List<FormationReponseDTO> toDTOList(List<Formation> formations);

    default LocalDateTime map(Timestamp value) {
        return value == null ? null : value.toLocalDateTime();
    }
}

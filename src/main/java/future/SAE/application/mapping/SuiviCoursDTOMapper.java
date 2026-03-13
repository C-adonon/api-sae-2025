package future.SAE.application.mapping;

import future.SAE.api.dto.SuiviCoursReponseDTO;
import future.SAE.api.dto.SuiviCoursRequeteDTO;
import future.SAE.infrastructure.mapping.CoursMapper;
import future.SAE.infrastructure.mapping.UtilisateurMapper;
import org.mapstruct.Mapper;

import future.SAE.domain.valueObject.SuiviCours;


@Mapper(componentModel = "spring", uses = { UtilisateurMapper.class, CoursMapper.class })
public interface SuiviCoursDTOMapper {

    SuiviCoursReponseDTO toDTO(SuiviCours domain);

    SuiviCours toDomain(SuiviCoursRequeteDTO suiviCoursDto);

}

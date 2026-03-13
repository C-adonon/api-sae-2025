package future.SAE.application.mapping;

import java.util.List;

import future.SAE.api.dto.InscriptionCoursReponseDTO;
import future.SAE.api.dto.InscriptionCoursRequeteDTO;
import future.SAE.infrastructure.mapping.CoursMapper;
import future.SAE.infrastructure.mapping.UtilisateurMapper;
import org.mapstruct.Mapper;


import future.SAE.domain.model.InscriptionCours;


@Mapper(componentModel = "spring", uses = { CoursMapper.class, UtilisateurMapper.class })
public interface InscriptionCoursDTOMapper {

    InscriptionCoursReponseDTO toDTO(InscriptionCours domain);

    InscriptionCours toDomain(InscriptionCoursRequeteDTO inscriptionCoursDto);
}

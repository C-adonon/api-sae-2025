package future.SAE.application.mapping;

import future.SAE.api.dto.FormationReponseDTO;
import future.SAE.api.dto.FormationRequeteDTO;
import org.mapstruct.Mapper;


import future.SAE.domain.model.Formation;
import future.SAE.infrastructure.persistence.FormationJPA;

@Mapper
public interface FormationDTOMapper {

    FormationReponseDTO toDTO(Formation domain);

    FormationJPA toDomain(FormationRequeteDTO formationDto);

}

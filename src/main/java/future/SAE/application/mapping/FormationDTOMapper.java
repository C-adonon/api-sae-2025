package future.SAE.application.mapping;

import future.SAE.api.dto.FormationReponseDTO;
import future.SAE.api.dto.FormationRequeteDTO;
import org.mapstruct.Mapper;


import future.SAE.domain.model.Formation;


import java.util.List;

@Mapper(componentModel = "spring")
public interface FormationDTOMapper {

    FormationReponseDTO toDTO(Formation domain);

    Formation toDomain(FormationRequeteDTO formationDto);

    List<Formation> toDomainList(List<FormationRequeteDTO> formationRequeteDTOList);

    List<FormationReponseDTO> toDTOList(List<Formation> formationList);

}

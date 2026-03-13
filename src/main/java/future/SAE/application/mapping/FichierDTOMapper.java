package future.SAE.application.mapping;


import future.SAE.api.dto.FichierReponseDTO;
import future.SAE.api.dto.FichierRequeteDTO;
import org.mapstruct.Mapper;

import future.SAE.domain.model.Fichier;


@Mapper(componentModel = "spring")
public interface FichierDTOMapper {

    FichierReponseDTO toDTO(Fichier domain);

    Fichier toDomain(FichierRequeteDTO fichierDto);
}

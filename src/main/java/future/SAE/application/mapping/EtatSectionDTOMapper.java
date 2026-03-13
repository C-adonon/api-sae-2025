package future.SAE.application.mapping;


import future.SAE.api.dto.EtatSectionReponseDTO;
import future.SAE.api.dto.EtatSectionRequeteDTO;
import future.SAE.infrastructure.mapping.SectionMapper;
import future.SAE.infrastructure.mapping.UtilisateurMapper;
import org.mapstruct.Mapper;

import future.SAE.domain.valueObject.EtatSection;


@Mapper(componentModel = "spring", uses = { UtilisateurMapper.class, SectionMapper.class })
public interface EtatSectionDTOMapper {

    EtatSectionReponseDTO toDto(EtatSection domain);

    EtatSection toDomain(EtatSectionRequeteDTO etatSectionDto);
 }

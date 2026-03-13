package future.SAE.application.mapping;

import java.util.List;

import future.SAE.api.dto.CompetenceReponseDTO;
import future.SAE.api.dto.CompetenceRequeteDTO;
import future.SAE.domain.model.Competence;
import future.SAE.infrastructure.mapping.UtilisateurMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import future.SAE.infrastructure.persistence.CompetenceJPA;

@Mapper(componentModel = "spring", uses = { UtilisateurMapper.class })
public interface CompetenceDTOMapper {

    Competence toDomain(CompetenceRequeteDTO requete);

    CompetenceReponseDTO toDTO(Competence domain);

 }

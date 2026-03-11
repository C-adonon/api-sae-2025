package future.SAE.infrastructure.mapping;

import java.util.List;

import org.mapstruct.Mapper;

import future.SAE.domain.model.Competence;
import future.SAE.infrastructure.persistence.CompetenceJPA;

@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class})
public interface CompetenceMapper {

    Competence toDomain(CompetenceJPA competenceJPA);

    CompetenceJPA toEntity(Competence competence);

    List<Competence> toDomainList(List<CompetenceJPA> competenceJPAList);

    List<CompetenceJPA> toEntityList(List<Competence> competenceList);

}

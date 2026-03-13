package future.SAE.infrastructure.mapping;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import future.SAE.domain.model.Cours;
import future.SAE.infrastructure.persistence.CoursJPA;

@Mapper(componentModel = "spring", uses = { UtilisateurMapper.class, FormationMapper.class })
public interface CoursMapper {
    @Mapping(target = "professeur", qualifiedByName = "toDomainSansCours")
    Cours toDomain(CoursJPA coursJPA);

    @Mapping(target = "inscriptions", ignore = true)
    CoursJPA toEntity(Cours cours);

    List<Cours> toDomainList(List<CoursJPA> coursJPAList);

    List<CoursJPA> toEntityList(List<Cours> coursList);

}

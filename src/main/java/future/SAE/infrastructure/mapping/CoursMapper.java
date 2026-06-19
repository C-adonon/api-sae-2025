package future.SAE.infrastructure.mapping;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import future.SAE.domain.model.Cours;
import future.SAE.infrastructure.persistence.entity.CoursJPA;

@Mapper(componentModel = "spring")
public interface CoursMapper {

    @Mapping(target = "professeur", ignore = true)
    @Mapping(target = "formation", ignore = true)
    @Mapping(target = "sections", ignore = true)
    @Mapping(target = "inscriptions", ignore = true)
    Cours toDomain(CoursJPA coursJPA);

    @Mapping(target = "professeur", ignore = true)
    @Mapping(target = "formation", ignore = true)
    @Mapping(target = "sections", ignore = true)
    @Mapping(target = "inscriptions", ignore = true)
    CoursJPA toEntity(Cours cours);

    List<Cours> toDomainList(List<CoursJPA> coursJPAList);

    List<CoursJPA> toEntityList(List<Cours> coursList);
}

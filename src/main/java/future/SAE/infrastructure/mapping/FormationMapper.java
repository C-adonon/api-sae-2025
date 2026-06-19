package future.SAE.infrastructure.mapping;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import future.SAE.domain.model.Formation;
import future.SAE.infrastructure.persistence.entity.FormationJPA;

@Mapper(componentModel = "spring")
public interface FormationMapper {

    @Mapping(target = "idFormation", source = "id")
    @Mapping(target = "responsable", ignore = true)
    @Mapping(target = "cours", ignore = true)
    Formation toDomain(FormationJPA formationJPA);

    @Mapping(target = "id", source = "idFormation")
    @Mapping(target = "responsable", ignore = true)
    @Mapping(target = "competences", ignore = true)
    FormationJPA toEntity(Formation formation);

    List<Formation> toDomainList(List<FormationJPA> formationJPAList);

    List<FormationJPA> toEntityList(List<Formation> formationList);
}

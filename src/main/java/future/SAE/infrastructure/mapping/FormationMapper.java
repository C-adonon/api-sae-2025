package future.SAE.infrastructure.mapping;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import future.SAE.domain.model.Formation;
import future.SAE.infrastructure.persistence.FormationJPA;

@Mapper(componentModel = "spring")
public interface FormationMapper {

    @Mapping(target = "responsable", ignore = true)
    Formation toDomain(FormationJPA formationJPA);

    @Mapping(target = "responsable", ignore = true)
    FormationJPA toEntity(Formation formation);

    @Named("ToDomainGeneralMapping")
    List<Formation> toDomainList(List<FormationJPA> formationJPAList);

    @Named("ToEntityGeneralMapping")
    List<FormationJPA> toEntityList(List<Formation> formationList);

}

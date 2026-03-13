package future.SAE.infrastructure.mapping;

import java.util.List;

import org.mapstruct.Mapper;

import future.SAE.domain.valueObject.SuiviCours;
import future.SAE.infrastructure.persistence.SuiviCoursJPA;

@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class, CoursMapper.class})
public interface SuiviCoursMapper {

    SuiviCours toDomain(SuiviCoursJPA suiviCoursJPA);

    SuiviCoursJPA toEntity(SuiviCours suiviCours);

    List<SuiviCours> toDomainList(List<SuiviCoursJPA> suiviCoursJPAList);

    List<SuiviCoursJPA> toEntityList(List<SuiviCours> suiviCoursList);

}

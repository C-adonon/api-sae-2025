package future.SAE.infrastructure.mapping;

import java.util.List;

import org.mapstruct.Mapper;

import future.SAE.domain.model.InscriptionCours;
import future.SAE.infrastructure.persistence.InscriptionCoursJPA;

@Mapper(componentModel = "spring", uses = { CoursMapper.class, UtilisateurMapper.class })
public interface InscriptionCoursMapper {

    InscriptionCours toDomain(InscriptionCoursJPA inscriptionCoursJPA);

    InscriptionCoursJPA toEntity(InscriptionCours inscriptionCours);

    List<InscriptionCours> toDomainList(List<InscriptionCoursJPA> inscriptionCoursJPAList);

    List<InscriptionCoursJPA> toEntityList(List<InscriptionCours> inscriptionCoursList);
}

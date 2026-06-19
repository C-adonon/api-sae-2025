package future.SAE.infrastructure.mapping;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import future.SAE.domain.model.InscriptionCours;
import future.SAE.infrastructure.persistence.entity.InscriptionCoursJPA;

@Mapper(componentModel = "spring", uses = { CoursMapper.class, UtilisateurMapper.class })
public interface InscriptionCoursMapper {

    @Mapping(target = "dateInscription", source = "date_inscription")
    InscriptionCours toDomain(InscriptionCoursJPA inscriptionCoursJPA);

    @Mapping(target = "date_inscription", source = "dateInscription")
    InscriptionCoursJPA toEntity(InscriptionCours inscriptionCours);

    List<InscriptionCours> toDomainList(List<InscriptionCoursJPA> inscriptionCoursJPAList);

    List<InscriptionCoursJPA> toEntityList(List<InscriptionCours> inscriptionCoursList);
}

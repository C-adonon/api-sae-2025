package future.SAE.application.mapping;

// import java.util.List;

import future.SAE.api.dto.CoursReponseDTO;
import future.SAE.api.dto.CoursRequeteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import future.SAE.domain.model.Cours;



@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CoursDTOMapper {
    @Mapping(target = "id", source = "idCours")
    @Mapping(target = "professeurId", source = "professeur.idUser")
    @Mapping(target = "formationId", source = "formation.idFormation")
    @Mapping(target = "dateCreation", source = "dateCreation")
    CoursReponseDTO toDTO(Cours cours);


    @Mapping(target = "idCours", ignore = true)
    @Mapping(target = "professeur.idUser", ignore = true)
    @Mapping(target = "formation.idFormation", source = "formationId")
    @Mapping(target = "inscriptions", ignore = true)
    @Mapping(target = "sections", ignore = true)
    @Mapping(target = "dateCreation", ignore = true)
    @Mapping(target = "dateModification", ignore = true)
    Cours toDomain(CoursRequeteDTO coursDTO);

}

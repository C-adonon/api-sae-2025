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
    @Mapping(target = "idCours", source = "idCours")
    @Mapping(target = "professeur", source = "professeur.idUser")
    @Mapping(target = "formation", source = "formation.idFormation")
    @Mapping(target = "dateCreation", source = "dateCreation")
    CoursReponseDTO toDTO(Cours cours);


    @Mapping(target = "idCours", ignore = true)
    @Mapping(target = "professeur.idUser", source = "idProfesseur")
    @Mapping(target = "formation.idFormation", source = "idFormation")
    @Mapping(target = "inscriptions", ignore = true)
    @Mapping(target = "sections", ignore = true)
    @Mapping(target = "dateCreation", ignore = true)
    @Mapping(target = "dateModification", ignore = true)
    Cours toDomain(CoursRequeteDTO coursDTO);

}

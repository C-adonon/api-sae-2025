package future.SAE.api.mapping;

import future.SAE.domain.model.Utilisateur;
import future.SAE.api.dto.requete.MiseAJourUtilisateurRequete;
import future.SAE.api.dto.reponse.UtilisateurReponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UtilisateurDTOMapper {


    UtilisateurReponse toResponse(Utilisateur utilisateur);

    List<UtilisateurReponse> toResponseList(List<Utilisateur> utilisateurs);

    void mettreAJourUtilisateur(MiseAJourUtilisateurRequete dto, @MappingTarget Utilisateur utilisateur);
}
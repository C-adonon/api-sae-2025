package future.SAE.application.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

import future.SAE.domain.model.Eleve;
import future.SAE.domain.model.Professeur;
import future.SAE.domain.model.Utilisateur;
import future.SAE.api.dto.*; // On importe tes DTO Requete et Reponse

@Mapper(componentModel = "spring")
public interface UtilisateurDTOMapper {

    // Cette méthode permet de transformer n'importe quel Utilisateur en son bon DTO de réponse
    default Object toResponse(Utilisateur domain) {
        if (domain == null) return null;
        if (domain instanceof Eleve) return toEleveResponse((Eleve) domain);
        if (domain instanceof Professeur) return toProfesseurResponse((Professeur) domain);
        return null;
    }

    // --- MAPPINGS ELEVE ---

    // 1. Requete -> Domaine (Pour la création/modification)
    @Mapping(target = "idUser", ignore = true)
    @Mapping(target = "formation.idFormation", source = "formationId")
    @Mapping(target = "motDePasse", source = "motDePasse")
    Eleve toEleveDomain(EleveRequeteDTO dto);

    // 2. Domaine -> Reponse (Pour l'affichage)
    @Mapping(target = "formationId", source = "formation.idFormation")
    @Mapping(target = "inscriptions", source = "inscriptions")
    EleveReponseDTO toEleveResponse(Eleve domain);


    // --- MAPPINGS PROFESSEUR ---

    // 1. Requete -> Domaine
    @Mapping(target = "idUser", ignore = true)
    Professeur toProfesseurDomain(ProfesseurRequeteDTO dto);

    // 2. Domaine -> Reponse
    @Mapping(target = "coursDispenses", source = "coursDispenses")
    ProfesseurReponseDTO toProfesseurResponse(Professeur domain);

    // --- LISTES ---
    List<EleveReponseDTO> toEleveResponseList(List<Eleve> domainList);
    List<ProfesseurReponseDTO> toProfesseurResponseList(List<Professeur> domainList);
}
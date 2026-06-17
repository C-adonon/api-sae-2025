package future.SAE.infrastructure.mapping;

import future.SAE.domain.model.Eleve;
import future.SAE.domain.model.Professeur;
import future.SAE.domain.model.Utilisateur;
import future.SAE.infrastructure.persistence.entity.EleveJPA;
import future.SAE.infrastructure.persistence.entity.ProfesseurJPA;
import future.SAE.infrastructure.persistence.entity.UtilisateurJPA;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    ProfesseurJPA toProfesseurEntity(Professeur professeur);
    EleveJPA toEleveEntity(Eleve eleve);

    Professeur toProfesseurDomain(ProfesseurJPA entity);
    Eleve toEleveDomain(EleveJPA entity);

    default UtilisateurJPA toEntity(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }
        if (utilisateur instanceof Professeur) {
            return toProfesseurEntity((Professeur) utilisateur);
        } else if (utilisateur instanceof Eleve) {
            return toEleveEntity((Eleve) utilisateur);
        }
        throw new IllegalArgumentException("Type d'utilisateur non supporté");
    }

    default Utilisateur toDomain(UtilisateurJPA entity) {
        if (entity == null) {
            return null;
        }
        if (entity instanceof ProfesseurJPA) {
            return toProfesseurDomain((ProfesseurJPA) entity);
        } else if (entity instanceof EleveJPA) {
            return toEleveDomain((EleveJPA) entity);
        }
        throw new IllegalArgumentException("Type d'entité JPA non supporté");
    }
}
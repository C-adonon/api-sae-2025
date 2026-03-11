package future.SAE.infrastructure.mapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

import future.SAE.domain.model.Eleve;
import future.SAE.domain.model.Professeur;
import future.SAE.domain.model.Utilisateur;
import future.SAE.infrastructure.persistence.EleveJPA;
import future.SAE.infrastructure.persistence.ProfesseurJPA;
import future.SAE.infrastructure.persistence.UtilisateurJPA;

@Mapper(componentModel = "spring", uses = { FormationMapper.class})
public interface UtilisateurMapper
{
    default UtilisateurMapper mapUtilisateurToDomain(UtilisateurJPA jpa)
    {
        if(jpa == null)
            return null;
        if(jpa instanceof EleveJPA)
            return toDomain((EleveJPA) jpa);
        if(jpa instanceof ProfesseurJPA)
            return toDomain((ProfesseurJPA) jpa);
        return null;
    }

    default UtilisateurJPA mapUtilisateurToEntity(Utilisateur domain)
    {
        if(domain == null)
            return null;
        if(domain instanceof Eleve)
            return toEntity((Eleve) domain);
        if(domain instanceof Professeur)
            return toEntity((Professeur) domain);
        return null;
    }

    Eleve toDomain(EleveJPA eleveJPA);
    EleveJPA toEntity(Eleve eleve);

    Professeur toDomain(ProfesseurJPA professeurJPA);

    @Named("toDomainSansCours")
    @Mapping(target = "coursDispenses", ignore = true)
    Professeur toDomainSansCours(ProfesseurJPA professeurJPA);

    ProfesseurJPA toEntity(Professeur professeur);

    List<Utilisateur> toDomainListFromMessage(List<UtilisateurJPA> utilisateurJPAList);
    List<UtilisateurJPA> toEntityListFromMessage(List<Utilisateur> utilisateurList);

    List<Eleve> toDomainList(List<EleveJPA> eleveJPAList);
    List<EleveJPA> toEntityList(List<Eleve> eleveist);

    List<Professeur> toDomainListFromCours(List<ProfesseurJPA> professeurJPAList);
    List<ProfesseurJPA> toEntityListFromCours(List<Professeur> professeurList);
}

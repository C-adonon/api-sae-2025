package future.SAE.infrastructure.mapping;

import java.util.List;

import future.SAE.domain.model.EtatSection;
import future.SAE.domain.model.Utilisateur;
import org.mapstruct.Mapper;

import future.SAE.infrastructure.persistence.EtatSectionJPA;

@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class, SectionMapper.class})
public interface EtatSectionMapper {

    EtatSection toDomain(EtatSectionJPA etatSectionJPA);

    EtatSectionJPA toEntity(EtatSection etatSection);

    List<EtatSection> toDomainList(List<EtatSectionJPA> etatSectionJPAList);

    List<EtatSectionJPA> toEntityList(List<EtatSection> etatSectionList);

}
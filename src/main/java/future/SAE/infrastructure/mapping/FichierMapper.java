package future.SAE.infrastructure.mapping;

import future.SAE.domain.model.Fichier;
import future.SAE.infrastructure.persistence.entity.FichierJPA;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FichierMapper {
    @Mapping(target = "section", ignore = true)
    FichierJPA toEntity(Fichier domaine);

    @Mapping(target = "section", ignore = true)
    Fichier toDomain(FichierJPA entity);

    List<Fichier> toDomainList(List<FichierJPA> entities);
}
package future.SAE.infrastructure.mapping;

import java.util.List;

import org.mapstruct.Mapper;
import future.SAE.domain.model.Fichier;
import future.SAE.infrastructure.persistence.FichierJPA;

@Mapper(componentModel = "spring")
public interface FichierMapper {

    // @Mapping(target = "coursDispenses", qualifiedByName = "toDomainSansCours")
    Fichier toDomain(FichierJPA fichierJPA);

    // @Mapping(target = "inscriptions", ignore = true)
    FichierJPA toEntity(Fichier fichier);

    List<Fichier> toDomainList(List<FichierJPA> fichierJPAList);

    List<FichierJPA> toEntityList(List<Fichier> fichierList);

}

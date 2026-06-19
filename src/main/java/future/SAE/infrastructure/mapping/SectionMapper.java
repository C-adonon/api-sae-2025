package future.SAE.infrastructure.mapping;

import future.SAE.domain.model.Section;
import future.SAE.infrastructure.mapping.FichierMapper;
import future.SAE.infrastructure.persistence.entity.SectionJPA;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FichierMapper.class}) // On réutilise le mapper Fichier !
public interface SectionMapper {
    @Mapping(target = "cours", ignore = true)
    SectionJPA toEntity(Section domaine);

    @Mapping(target = "cours", ignore = true)
    Section toDomain(SectionJPA entity);

    List<Section> toDomainList(List<SectionJPA> entities);
}
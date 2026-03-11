package future.SAE.infrastructure.mapping;

import java.util.List;

import org.mapstruct.Mapper;

import future.SAE.domain.model.Section;
import future.SAE.infrastructure.persistence.SectionJPA;

@Mapper(componentModel = "spring", uses = { CoursMapper.class, FichierMapper.class })
public interface SectionMapper {

    Section toDomain(SectionJPA sectionJPA);

    SectionJPA toEntity(Section section);

    List<Section> toDomainList(List<SectionJPA> sectionJPAList);

    List<SectionJPA> toEntityList(List<Section> sectionList);

}

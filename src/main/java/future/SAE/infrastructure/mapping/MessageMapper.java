package future.SAE.infrastructure.mapping;

import java.util.List;

import org.mapstruct.Mapper;

import future.SAE.domain.model.Message;
import future.SAE.infrastructure.persistence.MessageJPA;

@Mapper(componentModel = "spring", uses = { UtilisateurMapper.class })
public class MessageMapper {

    Message toDomain(MessageJPA messageJPA);

    MessageJPA toEntity(Message message);

    List<Message> toDomainList(List<MessageJPA> messageJPAList);

    List<MessageJPA> toEntityList(List<Message> messageList);

}

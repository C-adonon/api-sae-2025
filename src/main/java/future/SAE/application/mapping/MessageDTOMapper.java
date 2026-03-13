package future.SAE.application.mapping;


import java.util.List;

import future.SAE.api.dto.MessageReponseDTO;
import future.SAE.api.dto.MessageRequeteDTO;
import future.SAE.infrastructure.mapping.UtilisateurMapper;
import org.mapstruct.Mapper;

import future.SAE.domain.model.Message;


@Mapper(componentModel = "spring", uses = { UtilisateurMapper.class })
public interface MessageDTOMapper {

    Message toDomain(MessageRequeteDTO messageRequeteDTO);

    MessageReponseDTO toDTO(Message message);

    List<Message> toDomainList(List<MessageRequeteDTO> messageRequeteDTOList);

    List<MessageReponseDTO> toDTOList(List<Message> messageList);
}

package future.SAE.application.service;

import future.SAE.domain.model.Message;
import future.SAE.infrastructure.mapping.MessageMapper;
import future.SAE.infrastructure.persistence.MessageJPA;
import future.SAE.infrastructure.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public MessageService(MessageRepository messageRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    public Message createMessage(Message message) {
        MessageJPA jpaToSave = messageMapper.toEntity(message);
        MessageJPA jpaSaved = messageRepository.save(jpaToSave);
        return messageMapper.toDomain(jpaSaved);
    }

    public List<Message> getAllMessages() {
        List<MessageJPA> messagesJPA = messageRepository.findAll();
        return messageMapper.toDomainList(messagesJPA);
    }

    public Message getMessageById(UUID id) {
        MessageJPA messageJPA = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Le message avec l'id " + id + " n'existe pas."));
        return messageMapper.toDomain(messageJPA);
    }

    public Message updateMessage(UUID id, Message newMessage) {
        messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Impossible de modifier : le message avec l'ID " + id + " n'existe pas."));

        MessageJPA jpaToUpdate = messageMapper.toEntity(newMessage);
        jpaToUpdate.setIdMessage(id);

        MessageJPA jpaSaved = messageRepository.save(jpaToUpdate);
        return messageMapper.toDomain(jpaSaved);
    }

    public void deleteMessage(UUID id) {
        if (!messageRepository.existsById(id)) {
            throw new RuntimeException("Impossible de supprimer : le message " + id + " n'existe pas.");
        }
        messageRepository.deleteById(id);
    }
    public List<Message> getMessagesByExpediteurByDateAsc(UUID idExpediteur) {
        List<MessageJPA> messagesJPA = messageRepository.findByExpediteurIdUserOrderByDateAsc(idExpediteur);
        return messageMapper.toDomainList(messagesJPA);
    }

    public List<Message> getMessagesByExpediteurByDateDesc(UUID idExpediteur) {
        List<MessageJPA> messagesJPA = messageRepository.findByExpediteurIdUserOrderByDateDesc(idExpediteur);
        return messageMapper.toDomainList(messagesJPA);
    }

    public List<Message> getMessagesByDestinataireByDateAsc(UUID idDestinataire) {
        List<MessageJPA> messagesJPA = messageRepository.findByDestinatairesIdUserOrderByDateAsc(idDestinataire);
        return messageMapper.toDomainList(messagesJPA);
    }

    public List<Message> getMessagesByDestinataireByDateDesc(UUID idDestinataire) {
        List<MessageJPA> messagesJPA = messageRepository.findByDestinatairesIdUserOrderByDateDesc(idDestinataire);
        return messageMapper.toDomainList(messagesJPA);
    }
}
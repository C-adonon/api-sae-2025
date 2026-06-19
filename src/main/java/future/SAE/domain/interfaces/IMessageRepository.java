package future.SAE.domain.interfaces;

import future.SAE.domain.model.Message;

import java.util.List;
import java.util.UUID;

public interface IMessageRepository
{
    List<Message> trouverParDestinataire(UUID destinataireId);
    void sauvegarder(Message message);
}

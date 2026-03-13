package future.SAE.api.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionRequeteDTO
{
    private int ordre;
    private String titre;
    private String texte;
    private boolean ouverte;
    private int coursId;
}

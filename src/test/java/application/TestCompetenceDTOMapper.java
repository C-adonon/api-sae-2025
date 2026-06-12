package application;

import future.SAE.SaeApplication;
import future.SAE.api.dto.CompetenceReponseDTO;
import future.SAE.api.dto.CompetenceRequeteDTO;
import future.SAE.application.mapping.CompetenceDTOMapper;
import future.SAE.domain.model.Competence;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest(classes = SaeApplication.class)
public class TestCompetenceDTOMapper {

    @Autowired
    private CompetenceDTOMapper competenceDTOMapper;

    @Test
    @DisplayName("Devrait mapper correctement un CompetenceRequeteDTO vers le domaine")
    void testToDomain(){

        CompetenceRequeteDTO dto = new CompetenceRequeteDTO();
        dto.setNumero(1);
        dto.setLibelle("Développement d'application");
        dto.setDescription("API REST");

        Competence domain = competenceDTOMapper.toDomain(dto);

        Assert.isTrue(domain != null, "Le domaine ne doit pas être null");
        Assert.isTrue(dto.getNumero() == domain.getNumero(), "Les numéros doivent être égaux");
        Assert.isTrue(dto.getLibelle().equals(domain.getLibelle()), "Les libellés doivent être égaux");
        Assert.isTrue(dto.getDescription().equals(domain.getDescription()), "Les descriptions doivent être égales");
    }

    @Test
    @DisplayName("Devrait mapper correctement une Competence vers CompetenceReponseDTO")
    void testToDTO(){

        Competence domain = new Competence();
        domain.setNumero(2);
        domain.setLibelle("Sécurité informatique");
        domain.setDescription("Chiffrement et gestion des droits");

        CompetenceReponseDTO dto = competenceDTOMapper.toDTO(domain);

        Assert.isTrue(dto != null, "Le DTO ne doit pas être null");
        Assert.isTrue(domain.getNumero() == dto.getNumero(), "Les numéros doivent être égaux");
        Assert.isTrue(domain.getLibelle().equals(dto.getLibelle()), "Les libellés doivent être égaux");
        Assert.isTrue(domain.getDescription().equals(dto.getDescription()), "Les descriptions doivent être égales");
    }
}
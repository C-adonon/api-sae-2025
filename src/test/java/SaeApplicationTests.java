import future.SAE.application.interfaces.ISecurityProvider;
import future.SAE.domain.interfaces.IUtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import future.SAE.SaeApplication;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest(classes = SaeApplication.class)
class SaeApplicationTests {

	@MockitoBean
	private IUtilisateurRepository utilisateurRepository;

	@MockitoBean
	private ISecurityProvider securityProvider;

	@Test
	void contextLoads() {
		// Ce test vérifie simplement que l'application arrive à s'allumer sans crasher.
	}
}

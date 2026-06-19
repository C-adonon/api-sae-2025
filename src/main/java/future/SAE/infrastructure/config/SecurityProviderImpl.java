package future.SAE.infrastructure.config;

import future.SAE.application.interfaces.ISecurityProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityProviderImpl implements ISecurityProvider {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String hacher(String motDePasseClair) {
        return passwordEncoder.encode(motDePasseClair);
    }

    @Override
    public boolean verifier(String motDePasseClair, String motDePasseHache) {
        return passwordEncoder.matches(motDePasseClair, motDePasseHache);
    }
}
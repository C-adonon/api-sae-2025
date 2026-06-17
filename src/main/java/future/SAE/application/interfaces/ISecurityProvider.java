package future.SAE.application.interfaces;

public interface ISecurityProvider {
    public String hacher(String motDePasseEnClair);
    public boolean verifier(String motDePasseClair, String motDePasseHache);
}

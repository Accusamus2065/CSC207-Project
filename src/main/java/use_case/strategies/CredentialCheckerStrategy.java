package use_case.strategies;

public interface CredentialCheckerStrategy {
    boolean validUsername(String username);
    boolean validPassword(String password);
}

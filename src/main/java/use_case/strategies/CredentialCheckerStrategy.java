package use_case.strategies;

public interface CredentialCheckerStrategy {
    boolean validUsername(String username);

    boolean validPassword(String password);

    boolean validSex(String sex);

    boolean validGender(String gender);

    boolean validBloodType(String bloodType);
}

package entity.checker;

public interface StringCredentialChecker {
    boolean validUsername(String username);

    boolean validPassword(String password);

    boolean validSex(String sex);

    boolean validGender(String gender);

    boolean validBloodType(String bloodType);
}
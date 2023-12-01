package entity.checker;

public class CredentialCheckerFacade implements CredentialChecker {
    DoubleCredentialChecker doubleChecker = new RangeCredentialChecker();
    StringCredentialChecker stringChecker = new RegexCredentialChecker();

    @Override
    public boolean validHeight(double height) {
        return doubleChecker.validHeight(height);
    }

    @Override
    public boolean validWeight(double weight) {
        return doubleChecker.validWeight(weight);
    }

    @Override
    public boolean validUsername(String username) {
        return stringChecker.validUsername(username);
    }

    @Override
    public boolean validPassword(String password) {
        return stringChecker.validPassword(password);
    }

    @Override
    public boolean validSpecialty(String specialty) {
        return stringChecker.validSpecialty(specialty);
    }

    @Override
    public boolean validDegree(String degree) {
        return stringChecker.validDegree(degree);
    }

    @Override
    public boolean validSex(String sex) {
        return stringChecker.validSex(sex);
    }

    @Override
    public boolean validGender(String gender) {
        return stringChecker.validGender(gender);
    }

    @Override
    public boolean validBloodType(String bloodType) {
        return stringChecker.validBloodType(bloodType);
    }
}

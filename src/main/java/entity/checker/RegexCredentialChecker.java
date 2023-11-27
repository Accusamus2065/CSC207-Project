package entity.checker;

import java.util.regex.Pattern;

public class RegexCredentialChecker implements StringCredentialChecker {

    public RegexCredentialChecker() {
    }

    public boolean validUsername(String username) {
        String regex = "^\\w.{3,}$";
        return Pattern.matches(regex, username);
    }

    public boolean validPassword(String password) {
        String regex = "^(?=.*[a-zA-Z])(?=.*[0-9]).{5,}$";
        return Pattern.matches(regex, password);
    }

    // Validate Sex (assuming 'M', 'F', 'O' as valid inputs)
    public boolean validSex(String sex) {
        String regex = "^[MFO]$";
        return Pattern.matches(regex, sex);
    }

    // Validate Gender (more flexible, allowing any non-empty string)
    public boolean validGender(String gender) {
        return gender != null && !gender.trim().isEmpty();
    }

    // Validate Blood Type (A, B, AB, O with + or -)
    public boolean validBloodType(String bloodType) {
        String regex = "^(A|B|AB|O)[+-]$";
        return Pattern.matches(regex, bloodType);
    }
}

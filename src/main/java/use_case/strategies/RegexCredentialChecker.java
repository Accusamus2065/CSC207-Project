package use_case.strategies;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexCredentialChecker implements CredentialCheckerStrategy {

    public RegexCredentialChecker() { }

    public boolean validPassword(String password) {
        String regex = "^(?=.*[a-zA-Z])(?=.*[0-9]).{5,}$";
        return Pattern.matches(regex, password);
    }

    public boolean validUsername(String username) {
        String regex = "^\\w.{3,}$";
        return Pattern.matches(regex, username);
    }
}

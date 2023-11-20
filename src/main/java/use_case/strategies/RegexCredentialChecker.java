package use_case.strategies;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexCredentialChecker implements CredentialCheckerStrategy {

    public RegexCredentialChecker() { }

    public boolean validPassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9]).{5,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean validUsername(String username) {
        Pattern pattern = Pattern.compile("^\\w.{3,}$");
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
}

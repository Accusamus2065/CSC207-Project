package entity.checker;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegexCredentialCheckerTest {
    private static final RegexCredentialChecker checker = new RegexCredentialChecker();

    @Test
    public void validUsernameTest() {
        assertTrue(checker.validUsername("taml5"));
        assertFalse(checker.validUsername("a"));
    }

    @Test
    public void validPasswordTest() {
        assertTrue(checker.validPassword("AbcDE123"));
        assertFalse(checker.validPassword("a")); // too short
        assertFalse(checker.validPassword("aDfdvDG")); // no digits
    }

    @Test
    public void validGenderTest() {
        assertTrue(checker.validGender("male"));
        assertFalse(checker.validGender(""));
    }

    @Test
    public void validSexTest() {
        assertTrue(checker.validSex("M"));
        assertFalse(checker.validSex("A"));
    }

    @Test
    public void validBloodTypeTest() {
        assertTrue(checker.validBloodType("A+"));
        assertFalse(checker.validBloodType("M."));
    }
}
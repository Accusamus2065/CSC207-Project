package entity.checker;

import org.junit.Test;

import static org.junit.Assert.*;

public class CredentialCheckerFacadeTest {
    private static final CredentialCheckerFacade checker = new CredentialCheckerFacade();

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

    @Test
    public void validHeightTest() {
        assertTrue(checker.validHeight(1.72));
        assertFalse(checker.validHeight(-1.2));
        assertFalse(checker.validHeight(100));
    }

    @Test
    public void validWeightTest() {
        assertTrue(checker.validWeight(60));
        assertFalse(checker.validWeight(2));
        assertFalse(checker.validWeight(1000));
    }
}
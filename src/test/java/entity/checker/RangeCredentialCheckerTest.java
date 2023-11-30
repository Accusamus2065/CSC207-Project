package entity.checker;

import org.junit.Test;

import static org.junit.Assert.*;

public class RangeCredentialCheckerTest {
    private static final RangeCredentialChecker checker = new RangeCredentialChecker();
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
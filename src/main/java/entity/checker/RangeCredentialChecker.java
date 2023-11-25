package entity.checker;

public class RangeCredentialChecker implements DoubleCredentialChecker {
    private static final double MIN_HEIGHT = 0.5; // meters (50 cm)
    private static final double MAX_HEIGHT = 2.5; // meters (250 cm)
    private static final double MIN_WEIGHT = 10.0; // kilograms
    private static final double MAX_WEIGHT = 300.0; // kilograms

    public RangeCredentialChecker() {
    }

    @Override
    public boolean validHeight(double height) {
        return height >= MIN_HEIGHT && height <= MAX_HEIGHT;
    }

    @Override
    public boolean validWeight(double weight) {
        return weight >= MIN_WEIGHT && weight <= MAX_WEIGHT;
    }
}

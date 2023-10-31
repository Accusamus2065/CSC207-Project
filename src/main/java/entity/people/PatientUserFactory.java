package entity.people;


public class PatientUserFactory implements UserFactory {

    @Override
    public User create(String username, String password) {
        return new Patient(username, password);
    }

    public User create(String username, String password, char sex, String gender, float height, float weight, String bloodType) {
        Patient pat = (Patient) create(username, password);
        pat.setSex(sex);
        pat.setGender(gender);
        pat.setBloodType(bloodType);
        pat.setWeight(weight);
        pat.setHeight(height);
        return pat;
    }
}
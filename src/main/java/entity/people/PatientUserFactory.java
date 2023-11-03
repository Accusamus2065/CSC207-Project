package entity.people;


public class PatientUserFactory implements UserFactory {

    @Override
    public User create(String username, String password) {
        return new CommonPatient(username, password);
    }

    public User create(String username, String password, char sex, String gender, float height, float weight, String bloodType) {
        CommonPatient pat = (CommonPatient) create(username, password);
        pat.setSex(sex);
        pat.setGender(gender);
        pat.setBloodType(bloodType);
        pat.setWeight(weight);
        pat.setHeight(height);
        return pat;
    }
}
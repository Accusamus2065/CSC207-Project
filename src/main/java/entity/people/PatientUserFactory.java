package entity.people;

public class PatientUserFactory {
    public IPatient create(String username, String password) {
        return new CommonPatient(username, password);
    }

    public IPatient create(String username, String password, String sex, String gender, double height, double weight, String bloodType) {
        CommonPatient pat = (CommonPatient) create(username, password);
        pat.setSex(sex);
        pat.setGender(gender);
        pat.setBloodType(bloodType);
        pat.setWeight(weight);
        pat.setHeight(height);
        return pat;
    }
}

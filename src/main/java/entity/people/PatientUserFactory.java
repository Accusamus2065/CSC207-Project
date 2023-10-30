package entity.people;

import java.time.LocalDateTime;

public class PatientUserFactory implements UserFactory {

    @Override
    public User create(String name, String password, LocalDateTime ldt) {
        return new Patient(name, password, ldt);
    }

    public User create(String name, String password, LocalDateTime ldt, char sex, String gender, float height, float weight, String bloodType) {
        Patient pat = (Patient) create(name, password, ldt);
        pat.setSex(sex);
        pat.setGender(gender);
        pat.setBloodType(bloodType);
        pat.setWeight(weight);
        pat.setHeight(height);
        return pat;
    }
}

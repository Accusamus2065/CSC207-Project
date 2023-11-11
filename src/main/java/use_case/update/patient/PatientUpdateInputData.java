package use_case.update.patient;

public class PatientUpdateInputData {
    private final String oldUsername;
    private final String newUsername;
    private final String password;
    private final String repeatPassword;
    private final String sex;
    private final String gender;
    private final double height;
    private final double weight;
    private final String bloodType;

    public PatientUpdateInputData(String oldUsername,
                                  String newUsername,
                                  String password,
                                  String repeatPassword, String sex,
                                  String gender,
                                  double height,
                                  double weight,
                                  String bloodType) {
        this.oldUsername = oldUsername;
        this.newUsername = newUsername;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.sex = sex;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.bloodType = bloodType;
    }

    public String getOldUsername() {
        return oldUsername;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getSex() {
        return sex;
    }

    public String getGender() {
        return gender;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getBloodType() {
        return bloodType;
    }
}

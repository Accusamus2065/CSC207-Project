package interface_adapter.update.patient;

public class PatientUpdateState {
    private String username = "";
    private String newUsername = "";
    private String password = "";
    private String repeatPassword = "";
    private String sex;
    private String gender;
    private double height;
    private double weight;
    private String bloodType;
    private String error = null;
    private boolean isDoctor;

    public PatientUpdateState() {
    }

    public PatientUpdateState(PatientUpdateState copy) {
        username = copy.username;
        newUsername = copy.username;
        password = copy.password;
        repeatPassword = copy.repeatPassword;
        sex = copy.sex;
        gender = copy.gender;
        height = copy.height;
        weight = copy.weight;
        bloodType = copy.bloodType;
        error = copy.error;
        isDoctor = copy.isDoctor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }

    @Override
    public String toString() {
        return "PatientUpdateState{" +
                "username='" + username + '\'' +
                ", newUsername='" + newUsername + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                ", sex='" + sex + '\'' +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", bloodType='" + bloodType + '\'' +
                ", error='" + error + '\'' +
                ", isDoctor=" + isDoctor +
                '}';
    }
}

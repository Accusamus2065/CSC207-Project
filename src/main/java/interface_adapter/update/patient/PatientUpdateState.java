package interface_adapter.update.patient;

public class PatientUpdateState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private String repeatPassword = "";
    private String repeatPasswordError = null;
    private String sex;
    private String sexError = null;
    private String gender;
    private String genderError = null;
    private double height;
    private String heightError = null;
    private double weight;
    private String weightError = null;
    private String bloodType;
    private String bloodTypeError = null;
    private boolean isDoctor;
    private String newUsername = null;

    public PatientUpdateState() {
    }

    public PatientUpdateState(PatientUpdateState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
        password = copy.password;
        passwordError = copy.passwordError;
        repeatPassword = copy.repeatPassword;
        repeatPasswordError = copy.repeatPasswordError;
        sex = copy.sex;
        sexError = copy.sexError;
        gender = copy.gender;
        genderError = copy.genderError;
        height = copy.height;
        heightError = copy.heightError;
        weight = copy.weight;
        weightError = copy.weightError;
        bloodType = copy.bloodType;
        bloodTypeError = copy.bloodTypeError;
        isDoctor = copy.isDoctor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }

    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSexError() {
        return sexError;
    }

    public void setSexError(String sexError) {
        this.sexError = sexError;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGenderError() {
        return genderError;
    }

    public void setGenderError(String genderError) {
        this.genderError = genderError;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getHeightError() {
        return heightError;
    }

    public void setHeightError(String heightError) {
        this.heightError = heightError;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getWeightError() {
        return weightError;
    }

    public void setWeightError(String weightError) {
        this.weightError = weightError;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getBloodTypeError() {
        return bloodTypeError;
    }

    public void setBloodTypeError(String bloodTypeError) {
        this.bloodTypeError = bloodTypeError;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    @Override
    public String toString() {
        return "PatientUpdateState{" +
                "username='" + username + '\'' +
                ", usernameError='" + usernameError + '\'' +
                ", password='" + password + '\'' +
                ", passwordError='" + passwordError + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                ", repeatPasswordError='" + repeatPasswordError + '\'' +
                ", sex='" + sex + '\'' +
                ", sexError='" + sexError + '\'' +
                ", gender='" + gender + '\'' +
                ", genderError='" + genderError + '\'' +
                ", height=" + height +
                ", heightError='" + heightError + '\'' +
                ", weight=" + weight +
                ", weightError='" + weightError + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", bloodTypeError='" + bloodTypeError + '\'' +
                ", isDoctor=" + isDoctor +
                '}';
    }
}

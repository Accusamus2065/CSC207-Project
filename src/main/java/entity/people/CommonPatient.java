package entity.people;

public class CommonPatient implements IPatient {
    private final String username;
    private final String password;
    private String sex;
    private String gender;
    private double height;
    private double weight;
    private String bloodType;

    public CommonPatient(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addConversation() {
        // TODO: add conversation method (should add parameters to ensure the id of the other party)
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
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
}

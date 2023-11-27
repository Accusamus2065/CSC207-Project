package entity.people;

public interface IPatient extends User {
    void addConversation();

    String getSex();

    void setSex(String sex);

    String getGender();

    void setGender(String gender);

    double getHeight();

    void setHeight(double height);

    double getWeight();

    void setWeight(double weight);

    String getBloodType();

    void setBloodType(String bloodType);
}

package entity.people;

import entity.chat.Conversation;

import java.util.List;

public interface IPatient extends User{
    void addConversation();
    void setUsername(String username);
    void setPassword(String password);
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
    List<Conversation> getConvs();
    void setConvs(List<Conversation> convs);
}

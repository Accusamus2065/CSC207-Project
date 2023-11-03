package entity.people;

import entity.chat.Conversation;

import java.util.List;

public interface IPatient extends User{
    void addConversation();
    void setUsername(String username);
    void setPassword(String password);
    char getSex();
    void setSex(char sex);
    String getGender();
    void setGender(String gender);
    float getHeight();
    void setHeight(float height);
    float getWeight();
    void setWeight(float weight);
    String getBloodType();
    void setBloodType(String bloodType);
    List<Conversation> getConvs();
    void setConvs(List<Conversation> convs);
}

package entity.people;

import entity.chat.Conversation;

import java.util.ArrayList;
import java.util.List;

public class CommonPatient implements IPatient {
    private String username;
    private String password;
    private char sex;
    private String gender;
    private float height;
    private float weight;
    private String bloodType;
    private List<Conversation> convs;

    public CommonPatient(String username, String password) {
        this.username = username;
        this.password = password;
        this.convs = new ArrayList<>();
    }

    public void addConversation() {
        // TODO: add conversation method (should add parameters to ensure the id of the other party)
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public List<Conversation> getConvs() {
        return convs;
    }

    public void setConvs(List<Conversation> convs) {
        this.convs = convs;
    }
}

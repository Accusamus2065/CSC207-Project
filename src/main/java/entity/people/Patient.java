package entity.people;

import entity.chat.Conversation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Patient implements User {
    private String username;
    private String password;
    private char sex;
    private String gender;
    private float height;
    private float weight;
    private String bloodType;
    private final LocalDateTime ldt;

    private List<Conversation> convs;

    public Patient(String username, String password, LocalDateTime ldt) {
        this.username = username;
        this.password = password;
        this.convs = new ArrayList<>();
        this.ldt = ldt;
    }

    public void addConversation() {
        // TODO: add conversation method (should add parameters to ensure the id of the other party)
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}

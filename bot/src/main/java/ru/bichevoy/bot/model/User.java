package ru.bichevoy.bot.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
@Data
@Entity(name = "users")
public class User {

    @Id
    private Long chatId;

    private String firstName;

    private String lastName;

    private String userName;

    private Timestamp registeredAt;

//    public Long getChatId() {
//        return chatId;
//    }
//
//    public void setChatId(Long chatId) {
//        this.chatId = chatId;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public Timestamp getRegisteredAt() {
//        return registeredAt;
//    }
//
//    public void setRegisteredAt(Timestamp registeredAt) {
//        this.registeredAt = registeredAt;
//    }
}

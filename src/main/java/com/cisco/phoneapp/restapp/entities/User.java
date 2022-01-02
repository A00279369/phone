package com.cisco.phoneapp.restapp.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

/**
 * User representation
 *
 */
@Entity
public class User extends RepresentationModel<User> {

    @Id
    @Type(type="uuid-char")
    @Column(name = "user_Id")
    private UUID userId;
    @NotBlank(message = "username is mandatory")
    private String userName;


    @NotBlank(message = "password is mandatory")
    private String password;

    @NotBlank(message = "email is mandatory")
    @Email
    private String emailAddress;

    private String preferredPhoneNumber;

    @OneToMany(mappedBy="user")
    Set <Phone> phones;

    public User(){}

    public User(UUID userId, String userName, String password, String emailAddress, String preferredPhoneNumber) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
        this.preferredPhoneNumber = preferredPhoneNumber;
    }


    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPreferredPhoneNumber() {
        return preferredPhoneNumber;
    }

    public void setPreferredPhoneNumber(String preferredPhoneNumber) {
        this.preferredPhoneNumber = preferredPhoneNumber;
    }


}

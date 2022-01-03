package com.cisco.phoneapp.restapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

/**
 * User representation
 *
 */
@Entity
@Table(name = "user")
@JsonIgnoreProperties({"phones"})
public class User extends RepresentationModel<User> {

    @Id
    @Type(type="uuid-char")
    @Column(name = "user_Id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID userId;
    @NotBlank(message = "username is mandatory")
    private String userName;

    @NotBlank(message = "password is mandatory")
    private String password;

    @NotBlank(message = "email is mandatory")
    @Email
    private String emailAddress;

    private String preferredPhoneNumber;



    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    List<Phone> phones;

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


    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}

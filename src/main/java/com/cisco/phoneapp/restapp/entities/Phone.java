package com.cisco.phoneapp.restapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

/**
 * Users' phone represenatation.
 *
 *
 */
@Entity
@Table(name = "phone")
public class Phone extends RepresentationModel<Phone> {
    @Id
    @Type(type="uuid-char")
    private UUID phoneId;
    @NotBlank(message = "phoneName is mandatory")
    private String phoneName;
    @NotBlank(message = "phoneModel is mandatory")
    private String phoneModel;
    @NotBlank(message = "phoneNumber is mandatory")
    private String phoneNumber;


    @JsonIgnoreProperties
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User user;

    public Phone(UUID phoneId, String phoneName, String phoneModel, String phoneNumber, User user) {
        this.phoneId = phoneId;
        this.phoneName = phoneName;
        this.phoneModel = phoneModel;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }

    public Phone() {
    }

    public UUID getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(UUID phoneId) {
        this.phoneId = phoneId;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

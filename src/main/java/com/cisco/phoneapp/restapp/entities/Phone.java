package com.cisco.phoneapp.restapp.entities;

import org.hibernate.annotations.Type;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
public class Phone extends RepresentationModel<Phone> {
    @Id
    @Type(type="uuid-char")
//    @Column(name = "phoneId", columnDefinition = "BINARY(16)")

    private UUID phoneId;
    @NotBlank(message = "phoneName is mandatory")
    private String phoneName;
    @NotBlank(message = "phoneModel is mandatory")
    private String phoneModel;
    @NotBlank(message = "phoneNumber is mandatory")
    private String phoneNumber;

    @Type(type="uuid-char")
    private UUID userId;

    @ManyToOne
    @JoinColumn
    private User user;

    public Phone(UUID phoneId, String phoneName, String phoneModel, String phoneNumber, UUID userId) {
        this.phoneId = phoneId;
        this.phoneName = phoneName;
        this.phoneModel = phoneModel;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}

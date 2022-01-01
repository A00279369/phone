package com.cisco.phoneapp.restapp.models;

import com.cisco.phoneapp.restapp.entities.Phone;
import org.springframework.hateoas.RepresentationModel;
public class PhoneModel extends RepresentationModel<PhoneModel> {

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    private Phone phone;


}

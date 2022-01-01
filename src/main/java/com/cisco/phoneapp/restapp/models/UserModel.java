package com.cisco.phoneapp.restapp.models;

import com.cisco.phoneapp.restapp.entities.User;
import org.springframework.hateoas.RepresentationModel;

public class UserModel extends RepresentationModel<UserModel> {

    private User user;
}

package com.cisco.phoneapp.restapp.controllers;

import com.cisco.phoneapp.restapp.dto.UserDto;
import com.cisco.phoneapp.restapp.entities.Phone;
import com.cisco.phoneapp.restapp.entities.User;
import com.cisco.phoneapp.restapp.exceptions.PhoneNotFoundException;
import com.cisco.phoneapp.restapp.exceptions.UserNotFoundException;
import com.cisco.phoneapp.restapp.repositories.PhoneRepository;
import com.cisco.phoneapp.restapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    /**
    Add a user to the system
    */
    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto ){
     User user = UserMapper.mapToUser(userDto);
     user =userRepository.save(user);

     user.add(linkTo(methodOn(UserController.class).addUser(userDto)).withSelfRel());
     return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
    Delete a user from the system
    */
    @DeleteMapping("/user/{id}")
    @Transactional
    public void deleteUser(@PathVariable UUID id){
        try {
            userRepository.deleteById(id);
            phoneRepository.deletePhoneByUserId(id);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("Unable to find User with id :" + id);
        }
    }

     /**
     List users in the system
    */
     @GetMapping("/user")
     public List<User> users(){
         return userRepository.findAll().stream()
               .map( u -> u.add(linkTo(methodOn(UserController.class).users(u.getUserId())).withSelfRel()))
               .collect(Collectors.toList());
     }

    /**
     Single user in the system
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<User> users(@PathVariable UUID userId){
        return userRepository
                .findByUserId(userId)
                .map(p -> {p.add(linkTo(methodOn(UserController.class).users(userId)).withSelfRel());
                            return ResponseEntity.ok(p); }
                     )
                .orElseThrow(() -> new UserNotFoundException("User not found for :"+userId));
    }

    /**
     User phone in the system
     */
    @GetMapping("/user/{userId}/phone/{phoneId}")
    public ResponseEntity getPhone(@PathVariable UUID userId,@PathVariable UUID phoneId){
        return phoneRepository
                .findPhoneByPhoneIdAndUserId(phoneId,userId)
                .map(p -> {
                     p.add(linkTo(methodOn(UserController.class).getPhone(userId,phoneId)).withSelfRel());
                     p.add(linkTo(methodOn(UserController.class).users(userId)).withRel("user"));
                    return ResponseEntity.ok(p); }
                )
                .orElseThrow(() -> new PhoneNotFoundException("Phone not found for :"+phoneId));
    }

    /**
     Add a phone to a user
     */
    @PostMapping("/user/{userId}/phone")
    public ResponseEntity<Phone> addPhoneToUser(@PathVariable UUID userId, @RequestBody Phone phone ){
        Optional<User> userOpt =userRepository.findByUserId(userId);
        if(userOpt.isPresent()){
            phone.setUserId(userId);
            phone = phoneRepository.save(phone);
            phone.add(linkTo(methodOn(UserController.class).addPhoneToUser(userId,phone)).withSelfRel());
            return new ResponseEntity<>(phone, HttpStatus.CREATED);
        }
        else{
            throw new UserNotFoundException("User not found for id  : " + userId);
        }
    }

    /**
     Delete a user's phone
     */
    @DeleteMapping("/user/phone/{phoneId}")
    @Transactional
    public void deletePhone(@PathVariable UUID phoneId ) {
        Optional<Phone> phoneOpt = phoneRepository.findById(phoneId);
        if (phoneOpt.isPresent()) {
            phoneRepository.delete(phoneOpt.get());

            Optional<User> userOpt = userRepository.
                    findByPreferredPhoneNumber(phoneOpt.get().getPhoneNumber());
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                user.setPreferredPhoneNumber("");
                userRepository.save(user);
            }

        } else {
            throw new PhoneNotFoundException("Unable to delete Phone with id :" + phoneId);
        }
    }

    /**
     List a user's phones
     */
    @GetMapping("/user/{userId}/phone")
    public List<Phone> listPhones(@PathVariable UUID userId){
        return phoneRepository.findAllByUserId(userId)
                .stream()
                .map( u -> u.add(linkTo(methodOn(UserController.class).getPhone(u.getUserId(),u.getPhoneId())).withSelfRel()))
                .collect(Collectors.toList());
    }

    /**
     Update a user's preferred phone number
     */
    @PutMapping("/user/{userId}")
    public ResponseEntity updatePreferredPhoneNumber(@PathVariable UUID userId ,@RequestParam String preferredPhoneNumber){
      Optional<User> userOpt = userRepository.findById(userId);

      if(userOpt.isPresent()){
          User user = userOpt.get();
          user.setPreferredPhoneNumber(preferredPhoneNumber);
          userRepository.save(user);
          user.add(linkTo(methodOn(UserController.class).users(userId)).withSelfRel());
          return new ResponseEntity<>(user, HttpStatus.OK);
      }
      else{
          throw new UserNotFoundException("Unable to find User with id :" + userId);
      }

    }

}

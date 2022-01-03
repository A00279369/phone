package com.cisco.phoneapp.restapp.repositories;

import com.cisco.phoneapp.restapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    List<User>findAll();


    Optional<User> findByUserId(UUID id);

    Optional<User> findByPreferredPhoneNumber(String phoneNumber);


}

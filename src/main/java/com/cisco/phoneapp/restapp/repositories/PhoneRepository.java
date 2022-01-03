package com.cisco.phoneapp.restapp.repositories;

import com.cisco.phoneapp.restapp.entities.Phone;
import com.cisco.phoneapp.restapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, UUID> {

    List<Phone>findAll();

    List<Phone> findAllByUser(User user);

    void deletePhoneByUser(User user);

    Optional<Phone> findPhoneByPhoneIdAndUser(UUID phoneId ,User user);
}

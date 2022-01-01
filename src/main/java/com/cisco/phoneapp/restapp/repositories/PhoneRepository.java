package com.cisco.phoneapp.restapp.repositories;

import com.cisco.phoneapp.restapp.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, UUID> {

    List<Phone>findAll();

    List<Phone> findAllByUserId(UUID id);

    void deletePhoneByUserId(UUID userId);

    Optional<Phone> findPhoneByPhoneIdAndUserId(UUID userId ,UUID phoneId );
}

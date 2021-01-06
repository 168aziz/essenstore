package com.essenstore.repository;

import com.essenstore.entity.ActivationCode;
import com.essenstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface ActivationCodeRepository extends JpaRepository<ActivationCode, Long> {

    Optional<ActivationCode> findByCode(String code);

    Optional<ActivationCode> findByUser(User user);
}

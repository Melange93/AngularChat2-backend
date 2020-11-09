package com.reka.lakatos.angularchatbackend.repository;

import com.reka.lakatos.angularchatbackend.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findUserByUserName(String userName);
    Optional<AppUser> findAppUserByUserName(String userName);
    Optional<AppUser> findAppUserByEmail(String email);
}

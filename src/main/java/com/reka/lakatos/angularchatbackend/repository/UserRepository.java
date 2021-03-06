package com.reka.lakatos.angularchatbackend.repository;

import com.reka.lakatos.angularchatbackend.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findUserByUserName(String userName);
}

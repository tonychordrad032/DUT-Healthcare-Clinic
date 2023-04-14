package com.codesurfers.duthealthcareclinic.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("FROM tbl_users u WHERE u.deleted = 0 AND u.username = :username AND u.password = :password")
    User findByUsernameAndPassword(@RequestParam("username") String username,@RequestParam("password") String password);
}

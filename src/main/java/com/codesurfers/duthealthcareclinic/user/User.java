package com.codesurfers.duthealthcareclinic.user;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "tbl_users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;

    @Column(length = 999, unique = true)
    private String username, studentNumber;

    //@Transient
    private String password;

    private String userType = "student";
    private int deleted = 0;
    private String profilePicture;

    private String mobile;
    private String qualification;
}

package com.codesurfers.healthcare.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
    private long userId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;

    private String username, studentNumber;
    private String password;
    private String profilePicture;

    private String userType;

    private String mobile;
    private String qualification;

    public User() {
    }

    public User(String firstName, String lastName, String dateOfBirth, String username, String studentNumber, String password, String userType, String mobile, String qualification) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.studentNumber = studentNumber;
        this.password = password;
        this.userType = userType;
        this.mobile = mobile;
        this.qualification = qualification;
    }

    public User(String firstName, String lastName, String username, String studentNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.studentNumber = studentNumber;
        this.password = password;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    protected User(Parcel in) {
        userId = in.readLong();
        firstName = in.readString();
        lastName = in.readString();
        dateOfBirth = in.readString();
        username = in.readString();
        studentNumber = in.readString();
        password = in.readString();
        profilePicture = in.readString();
        userType = in.readString();
        mobile = in.readString();
        qualification = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getUserType() {
        return userType;
    }

    public String getMobile() {
        return mobile;
    }

    public String getQualification() {
        return qualification;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", username='" + username + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", password='" + password + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", userType='" + userType + '\'' +
                ", mobile='" + mobile + '\'' +
                ", qualification='" + qualification + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(userId);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(dateOfBirth);
        dest.writeString(username);
        dest.writeString(studentNumber);
        dest.writeString(password);
        dest.writeString(profilePicture);
        dest.writeString(userType);
        dest.writeString(mobile);
        dest.writeString(qualification);
    }
}
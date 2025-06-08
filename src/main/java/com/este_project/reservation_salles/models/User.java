package com.este_project.reservation_salles.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String emailAcademic;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String officeNumber;
    private String speciality;
    private String department;
    private Boolean isActive;

    public User() {
    }

    public User(Integer id, String firstName, String lastName, String emailAcademic, String password,
                Role role, String officeNumber, String speciality, String department, Boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAcademic = emailAcademic;
        this.password = password;
        this.role = role;
        this.officeNumber = officeNumber;
        this.speciality = speciality;
        this.department = department;
        this.isActive = isActive;
    }

    // Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAcademic() {
        return emailAcademic;
    }

    public void setEmailAcademic(String emailAcademic) {
        this.emailAcademic = emailAcademic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public enum Role {
        admin,
        professor
    }
}

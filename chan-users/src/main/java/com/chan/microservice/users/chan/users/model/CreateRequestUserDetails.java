package com.chan.microservice.users.chan.users.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class CreateRequestUserDetails {

    @NotNull(message = "First Name can not be empty")
    private String firstName;

    @NotNull(message = "Last Name can not be empty")
    private String lastName;

    @NotNull(message = "password can not be empty")
    private String password;

    @NotNull(message = "Email can not be empty")
    @Email(message = "Invalid email")
    private String email;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}

package com.microservice.rest.api.controller;

import com.microservice.rest.api.model.UserDetail;
import com.microservice.rest.api.response.IUserDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")  // eq., http://localhost:8080/users
public class UserController {

    @Autowired
    private IUserDetailsResponse userDetailsResponse;

    @GetMapping
    public String getUsers(@RequestParam(value="page") int page, @RequestParam(value="limit") int limit) {
        return "get user page " + page + " limit " + limit;
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserDetail> getUser(@PathVariable String userId) {
        return new ResponseEntity<UserDetail>(userDetailsResponse.createUser(userId), HttpStatus.OK);
    }

    @PostMapping
    public String createUser() {
        return "create user";
    }

    @PutMapping
    public String updateUser() {
        return "update user";
    }

    @DeleteMapping
    public String deleteUser() {
        return "delete user";
    }
}

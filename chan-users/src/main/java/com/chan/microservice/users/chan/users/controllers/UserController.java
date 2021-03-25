package com.chan.microservice.users.chan.users.controllers;

import com.chan.microservice.users.chan.users.model.CreateRequestUserDetails;
import com.chan.microservice.users.chan.users.model.UserResponseModel;
import com.chan.microservice.users.chan.users.response.CreatedUserDetails;
import com.chan.microservice.users.chan.users.service.UsersService;
import com.chan.microservice.users.chan.users.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UsersService usersService;

    @GetMapping("/status/check")
    public String status() {
        return "working!!!";
    }

    @PostMapping
    public ResponseEntity<CreatedUserDetails> createUser(@Valid @RequestBody CreateRequestUserDetails userDetails) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(userDetails, UserDto.class);
        usersService.createUser(userDto);

        CreatedUserDetails user = mapper.map(userDto, CreatedUserDetails.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping(value="/{userId}")
    public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId){

        UserDto userDto = usersService.getUserByUserId(userId);
        UserResponseModel userResponseModel = new ModelMapper().map(userDto, UserResponseModel.class);

        return ResponseEntity.status(HttpStatus.OK).body(userResponseModel);

    }
}

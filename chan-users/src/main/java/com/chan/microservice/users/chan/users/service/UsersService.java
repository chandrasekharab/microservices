package com.chan.microservice.users.chan.users.service;

import com.chan.microservice.users.chan.users.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {

    public UserDto createUser(UserDto userDatails);
    public UserDto getUserDetailsByEmail(String email);
    public UserDto getUserByUserId(String userId);
}

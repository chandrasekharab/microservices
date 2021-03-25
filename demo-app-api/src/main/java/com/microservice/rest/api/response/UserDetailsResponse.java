package com.microservice.rest.api.response;

import com.microservice.rest.api.model.UserDetail;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsResponse implements IUserDetailsResponse{

    public UserDetail createUser(String userId) {
        UserDetail userDetail = new UserDetail();
        userDetail.setLastName("last Name");
        userDetail.setFirstName("first name");
        return userDetail;
    }
}

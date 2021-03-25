package com.microservice.rest.api.response;

import com.microservice.rest.api.model.UserDetail;

public interface IUserDetailsResponse {
    public UserDetail createUser(String userId);
}

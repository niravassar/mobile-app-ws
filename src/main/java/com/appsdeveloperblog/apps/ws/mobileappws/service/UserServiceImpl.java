package com.appsdeveloperblog.apps.ws.mobileappws.service;

import com.appsdeveloperblog.apps.ws.mobileappws.controller.UserDetailsRequestModel;
import com.appsdeveloperblog.apps.ws.mobileappws.controller.UserRest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private Map<String, UserRest> users;

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetailsRequestModel) {
        UserRest userRest = new UserRest();
        userRest.setEmail(userDetailsRequestModel.getEmail());
        userRest.setFirstName(userDetailsRequestModel.getFirstName());
        userRest.setLastName(userDetailsRequestModel.getLastName());

        String userId = UUID.randomUUID().toString();
        userRest.setUserId(userId);

        if (users == null) {
            users = new HashMap<>();
        }
        users.put(userId, userRest);

        return userRest;
    }
}

package com.appsdeveloperblog.apps.ws.mobileappws.service;

import com.appsdeveloperblog.apps.ws.mobileappws.controller.UserDetailsRequestModel;
import com.appsdeveloperblog.apps.ws.mobileappws.controller.UserRest;

public interface UserService {

    UserRest createUser(UserDetailsRequestModel userDetailsRequestModel);
}

package com.appsdeveloperblog.apps.ws.mobileappws;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public String getUsers(@RequestParam(value="page", defaultValue = "1") int page,
                           @RequestParam(value="limit", defaultValue= "50") int limit,
                           @RequestParam(value="sort", required = false) String sort) {
        return "getUser was called page = " + page + " and limit = " + limit + " and sort = " + sort;
    }

    @GetMapping(path = "/{userId}")
    public UserRest getUser(@PathVariable String userId) {
        UserRest userRest = new UserRest();
        userRest.setEmail("test@test.com");
        userRest.setFirstName("Nirav");
        userRest.setLastName("Assar");
        return userRest;
    }

    @PostMapping
    public String createUser() {
        return "createUser was called";
    }

    @PutMapping
    public String updateUser() {
        return "updateUser was called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "deleteUser was called";
    }

}

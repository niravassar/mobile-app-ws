package com.appsdeveloperblog.apps.ws.mobileappws.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

    Map<String, UserRest> users;

    @GetMapping
    public String getUsers(@RequestParam(value="page", defaultValue = "1") int page,
                           @RequestParam(value="limit", defaultValue= "50") int limit,
                           @RequestParam(value="sort", required = false) String sort) {
        return "getUser was called page = " + page + " and limit = " + limit + " and sort = " + sort;
    }

    @GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
        String firstName = null; // NPE
        int firstNameLength = firstName.length();


        if(users.containsKey(userId)) {
            return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<UserRest> createUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel) {
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

        return new ResponseEntity<UserRest>(userRest, HttpStatus.OK);
    }

    @PutMapping(path = "/{userId}", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
                                    produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserRest> updateUser(@PathVariable String userId, @RequestBody UserDetailsRequestModel userDetailsRequestModel) {
        if(users.containsKey(userId)) {
            UserRest storedUserDetails = users.get(userId);
            storedUserDetails.setFirstName(userDetailsRequestModel.getFirstName());
            storedUserDetails.setLastName(userDetailsRequestModel.getLastName());
            users.put(userId, storedUserDetails);

            return new ResponseEntity<UserRest>(storedUserDetails, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity deleteUser(@PathVariable String userId) {
        users.remove(userId);
        return ResponseEntity.noContent().build();
    }

}
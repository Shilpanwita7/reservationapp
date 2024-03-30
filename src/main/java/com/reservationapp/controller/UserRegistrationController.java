package com.reservationapp.controller;


import com.reservationapp.entity.UserRegistration;
import com.reservationapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//v1 here the version1
@RestController
@RequestMapping("/api/v1/user")
public class UserRegistrationController {

    @Autowired
    private UserServiceImpl userService;

//    MultipartFile is the datatype of profilePicture in db, the datatype to catch an image from postman should
//    be MultipartFile
    @PostMapping
    public String createUser(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("profilePicture") MultipartFile profilePicture
    ){
//to return back here a response we will use payload, there will create dto class
//getBytes() in profilePicture was throwing error so try-catch
        try{
            UserRegistration userRegistration= new UserRegistration();
            userRegistration.setName(name);
            userRegistration.setEmail(email);
            userRegistration.setPassword(password);
            userRegistration.setProfilePicture(profilePicture.getBytes());

            userService.createUser(userRegistration);
        }catch (Exception e){
           e.printStackTrace();
        }
        return "done";
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRegistration> getUserById(@PathVariable long id){
        UserRegistration user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

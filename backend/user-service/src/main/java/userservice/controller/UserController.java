package userservice.controller;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import userservice.dto.RegistrationRequest;
import userservice.dto.UserRequest;
import userservice.dto.UserResponse;
import userservice.service.RegistrationService;
import userservice.service.UserService;

@RestController
@RequestMapping(path = "users/")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationService registrationService;

    Environment environment;

    @GetMapping(path = "details")
    public UserResponse getUser(@RequestHeader(name = "Authorization") String token) {
        return userService.getUser(token);
    }

    @GetMapping(path = "validateUsername")
    public HashMap validateUsername(@RequestParam("username") String username) {
        HashMap<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("isUsernameUnique", userService.validateUsername(username));
        return map;
    }

    @GetMapping(path = "validateEmail")
    public HashMap validateEmail(@RequestParam("email") String email) {
        HashMap<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("isEmailUnique", userService.validateEmail(email));
        return map;
    }

    @PostMapping(path = "register")
    public String registerUser(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @PutMapping(path = "update")
    public void updateUser(@RequestHeader(name = "Authorization") String token, @RequestBody UserRequest request) {
        userService.updateUser(token, request);
    }

    @GetMapping(path = "register/resend")
    public String registerUser(@RequestParam("email") String email) {
        return registrationService.resendToken(email);

    }

    @GetMapping(path = "register/confirm", produces = "text/html;charset=UTF-8")
    public String confirmRequestParam(@RequestParam("token") String token) {
        registrationService.confirmToken(token);
        return "<div>Your account has been successfully verified please click <a href='"
                + environment.getProperty("frontend_url") + "'>here</a> to visit the stock market page.</div>";

    }
}
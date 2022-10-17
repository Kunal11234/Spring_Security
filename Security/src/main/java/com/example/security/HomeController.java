package com.example.security;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    //this is basically to check the  permissions or its a fake mapping
    @GetMapping("/")
    public String getHome() {
        return ("<h1>Welcome</h1>");
    }

    //this is basically to check the  permissions for the admin

    @GetMapping("/admin")
    public String getAdmin() {
        return ("<h1>Welcome Admin</h1>");
    }

    //this is basically to check the  permissions for the user
    @GetMapping("/user")
    public String getUser() {
        return ("<h1>Welcome User</h1>");
    }

}

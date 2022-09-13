package com.unittest.demo;

import com.unittest.demo.models.TestModel;
import com.unittest.demo.models.UserResource;
import com.unittest.demo.service.WelcomeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
public class IntegrationController {

    @Autowired
    WelcomeService welcomeService;

    @Autowired
    UserRepo userRepo;

    public IntegrationController(WelcomeService welcomeService){
        this.welcomeService = welcomeService;
    }

    public IntegrationController(){
    }

    @GetMapping("/welcome-integration")
    public String welcome(@RequestParam(defaultValue = "Stranger") String name) {
        return welcomeService.getWelcomeMessage(name);
    }

    @PostMapping("/post-integration")
    public TestModel postMessage(@RequestBody TestModel testModel) {
        return testModel;
    }


    @PostMapping(value = "/add-user",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResource addUser(@RequestBody UserResource userResource) {
        userRepo.save(userResource);
        return userRepo.getByEmail(userResource.getEmail());
    }

}



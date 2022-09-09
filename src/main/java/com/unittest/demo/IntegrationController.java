package com.unittest.demo;

import com.unittest.demo.models.TestModel;
import com.unittest.demo.service.WelcomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class IntegrationController {

    @Autowired
    WelcomeService welcomeService;

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

}



package com.unittest.demo;

import com.unittest.demo.service.WelcomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UnitController {

    WelcomeService welcomeService;

    public UnitController(WelcomeService welcomeService){
        this.welcomeService = welcomeService;
    }

    public UnitController(){

    }

    @GetMapping("/")
    public String welcome(@RequestParam(defaultValue = "Stranger") String name) {
        return welcomeService.getWelcomeMessage(name);
    }

    @GetMapping("/unit-test")
    public String testType(){
        return "Unit Test";
    }
}



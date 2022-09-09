package com.unittest.demo;

import com.unittest.demo.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AcceptanceController {

    @Autowired
    WelcomeService welcomeService;

    @GetMapping("/acceptance-integration")
    public String welcome(@RequestParam(defaultValue = "Stranger") String name) {
        return welcomeService.getWelcomeMessage(name);
    }
}

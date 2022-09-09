package com.unittest.demo;

import com.unittest.demo.service.WelcomeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UnitControllerUnitTest {

    @Test
    void testType() {
        UnitController unitController = new UnitController();
        assertEquals("Unit Test",unitController.testType());
    }

    @Test
    void welcome() {
        WelcomeService welcomeService = Mockito.mock(WelcomeService.class);
        when(welcomeService.getWelcomeMessage("David")).thenReturn("Welcome David");
        UnitController myController = new UnitController(welcomeService);
        assertEquals("Welcome David",myController.welcome("David"));
    }

}
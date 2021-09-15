package com.vrac.restservice.controller;

import com.vrac.restservice.service.VracService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VracController {

    @Autowired
    private VracService vracService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the VRAC tool center ";
    }

    @GetMapping("/random/{min}/{max}")
    public String generateRandomInt(@PathVariable int min, @PathVariable int max) {
        int randomNumber = vracService.generateInt(min, max);
        return String.format("[%d, %d] => %d", min, max, randomNumber);
    }

}

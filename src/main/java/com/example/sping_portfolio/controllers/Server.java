package com.example.sping_portfolio.controllers;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class Server {
    
    // @GetMapping
    // public List<Client> getClients() {
        // return clientRepository.findAll();
    // }

    @GetMapping("/test")
    public String getServer() {
        return "TEST GET REQUEST";
    }
}

package com.artesanos.sistema_pedidos.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class UserController {

    @GetMapping("/lon")
    public ResponseEntity<?> getMethodName() {
        return ResponseEntity.ok().build();
    }

}

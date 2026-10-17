package com.shophook.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhooks")
public class WebhookController {

    @PostMapping("/payments")
    public ResponseEntity<Void> receive(@RequestBody String rawBody) {
        System.out.println("Got webhook: " + rawBody);
        return ResponseEntity.ok().build();
    }
}
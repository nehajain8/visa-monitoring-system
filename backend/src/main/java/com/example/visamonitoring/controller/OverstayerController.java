package com.example.visamonitoring.controller;

import com.example.visamonitoring.service.OverstayerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/overstayers")
@CrossOrigin(origins = "*")
public class OverstayerController {
    private final OverstayerService service;
    public OverstayerController(OverstayerService service) {
        this.service = service;
    }

    @GetMapping
    public String placeholder() {
        return "Overstayer list - TODO";
    }
}

package com.example.visamonitoring.controller;

import com.example.visamonitoring.dto.OverstayerDto;
import com.example.visamonitoring.service.OverstayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/overstayers")
@RequiredArgsConstructor
public class OverstayerController {

    private final OverstayerService overstayerService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OverstayerDto>> getOverstayers() {
        List<OverstayerDto> overstayers = overstayerService.getCurrentOverstayers();
        return ResponseEntity.ok(overstayers);
    }
}

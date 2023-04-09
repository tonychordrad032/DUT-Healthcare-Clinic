package com.codesurfers.duthealthcareclinic.clinic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clinic")
public class ClinicController {
    @Autowired
    private ClinicService clinicService;

    @PostMapping
    public ResponseEntity save(@RequestBody Clinic clinic){
        String correlationId = UUID.randomUUID().toString();
        return clinicService.save(clinic, correlationId);
    }

    @GetMapping
    public List listAll(){
        return clinicService.listAll();
    }
}

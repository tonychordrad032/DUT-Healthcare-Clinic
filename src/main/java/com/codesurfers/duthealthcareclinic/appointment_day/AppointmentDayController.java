package com.codesurfers.duthealthcareclinic.appointment_day;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/appointment-day")
public class AppointmentDayController {
    @Autowired
    private AppointmentDayService appointmentDayService;

    @PostMapping()
    public ResponseEntity save(@RequestBody AppointmentDay appointmentDay){
        String correlationId = UUID.randomUUID().toString();
        return appointmentDayService.save(appointmentDay, correlationId);
    }

    @GetMapping
    public List listAll(){
        return appointmentDayService.listAll();
    }
}

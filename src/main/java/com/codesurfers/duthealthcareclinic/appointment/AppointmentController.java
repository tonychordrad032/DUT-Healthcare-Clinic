package com.codesurfers.duthealthcareclinic.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    //@RolesAllowed({"admin", "user"})
    public ResponseEntity save(@RequestBody Appointment appointment){
        String correlationId = UUID.randomUUID().toString();
        return appointmentService.save(appointment, correlationId);
    }

    @GetMapping
    //@RolesAllowed({"admin", "user"})
    public List listAll() {
        return appointmentService.listAll();
    }

    @PutMapping
    //@RolesAllowed({"admin", "user"})
    public ResponseEntity update(@RequestBody Appointment appointment){
        String correlationId = UUID.randomUUID().toString();
        return appointmentService.update(appointment, correlationId);
    }

    @DeleteMapping("{id}")
    //@RolesAllowed({"admin", "user"})
    public ResponseEntity delete(@PathVariable("id") long id){
        String correlationId = UUID.randomUUID().toString();
        return appointmentService.delete(id, correlationId);
    }
}

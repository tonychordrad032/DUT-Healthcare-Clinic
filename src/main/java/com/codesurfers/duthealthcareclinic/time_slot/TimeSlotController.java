package com.codesurfers.duthealthcareclinic.time_slot;

import com.codesurfers.duthealthcareclinic.user.User;
import com.codesurfers.duthealthcareclinic.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/time-slot")
public class TimeSlotController {
    @Autowired
    private TimeSlotService timeSlotService;

    @PostMapping()
    public ResponseEntity save(@RequestBody TimeSlot timeSlot){
        String correlationId = UUID.randomUUID().toString();
        return timeSlotService.save(timeSlot, correlationId);
    }

    @GetMapping
    public List listAll(){
        return timeSlotService.listAll();
    }

    @GetMapping("/find-by-day")
    public ResponseEntity findTimeSlotByDay(@RequestParam("day") String day){
        String correlationId = UUID.randomUUID().toString();
        return timeSlotService.findTimeSlotByDay(day, correlationId);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody TimeSlot timeSlot){
        String correlationId = UUID.randomUUID().toString();
        return timeSlotService.update(timeSlot, correlationId);
    }

    @GetMapping("/refresh-time-slot")
    public ResponseEntity resetTimeSlots(){
        String correlationId = UUID.randomUUID().toString();
        return timeSlotService.resetTimeSlots(correlationId);
    }

}

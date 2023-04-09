package com.codesurfers.duthealthcareclinic.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/feedback")
public class FeedBackController {
    @Autowired
    private FeedBackService feedBackService;

    @PostMapping()
    //@RolesAllowed("admin")
    public ResponseEntity save(@RequestBody FeedBack feedBack){
        String correlationId = UUID.randomUUID().toString();
        return feedBackService.save(feedBack, correlationId);
    }

    @GetMapping
    //@RolesAllowed({"admin", "user"})
    public List listAll(){
        return feedBackService.listAll();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody FeedBack feedBack){
        String correlationId = UUID.randomUUID().toString();
        return feedBackService.update(feedBack, correlationId);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam("id") long id){
        String correlationId = UUID.randomUUID().toString();
        return feedBackService.delete(id, correlationId);
    }


}

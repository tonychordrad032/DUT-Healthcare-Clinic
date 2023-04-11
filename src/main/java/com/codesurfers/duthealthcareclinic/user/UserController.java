package com.codesurfers.duthealthcareclinic.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    //@RolesAllowed("admin")
    public ResponseEntity save(@RequestBody User user){
        String correlationId = UUID.randomUUID().toString();
        return userService.save(user, correlationId);
    }

    @GetMapping
    //@RolesAllowed({"admin", "user"})
    public List listAll(){
        return userService.listAll();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody User user){
        String correlationId = UUID.randomUUID().toString();
        return userService.update(user, correlationId);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam("id") long id){
        String correlationId = UUID.randomUUID().toString();
        return userService.delete(id, correlationId);
    }

}

package com.codesurfers.duthealthcareclinic.user;

import com.codesurfers.duthealthcareclinic.utils.helpers.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * Saves user to Database using JPA
     * @param user
     * @return
     */

    public ResponseEntity save(User user, String correlationId) {
        try {
            LOG.info("{} : Start saving user", correlationId);

            if (user == null || user.getUsername().equals("")) {
                LOG.warn("{} : no content", correlationId);
                return ResponseEntity.noContent().build();
            }

            User user1 = userRepository.findByUsername(user.getUsername());

            if (user1 != null) {
                LOG.warn("{} : User with email " + user.getUsername() + " already exist");
                return ResponseEntity.status(409).body(new ResponseResult(409, "User with email " +user.getUsername() + " Already exist", null));

            }

            userRepository.save(user);
            userRepository.flush();

            LOG.info("{} : User was added successfully", correlationId);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getUserId()).toUri();

            return ResponseEntity.created(location).
                    body(new ResponseResult(201, "User was added successfully", null));
        }catch (Exception e){
            LOG.error("{} : Error while saving user", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }finally {
            LOG.info("{} : Done saving user", correlationId);
        }
    }

    /**
     * Returns all the users
     * @return
     */
    public List<User> listAll() {

        return userRepository.findAll()
                .stream()
                .filter(user -> user.getDeleted() == 0)
                .collect(Collectors.toList());
    }


    public ResponseEntity update(User user, String correlationId){
        try {
            LOG.info("{} : Start updating user", correlationId);

            if (user == null){
                LOG.warn("{} : no content", correlationId);
                return ResponseEntity.noContent().build();
            }

            User _user = userRepository.findById(user.getUserId()).orElseThrow(() -> new RuntimeException("User Not found"));

            _user.setFirstName(user.getFirstName());
            _user.setLastName(user.getLastName());
            _user.setDateOfBirth(user.getDateOfBirth());
            _user.setStudentNumber(user.getStudentNumber());
            _user.setQualification(user.getQualification());
            _user.setMobile(user.getMobile());
            _user.setProfilePicture(user.getProfilePicture());

            userRepository.save(_user);
            userRepository.flush();
            LOG.info("{} : User was successfully updated", correlationId);

            return ResponseEntity.ok().body(new ResponseResult(200, "User was updated successfully", _user));

        }catch (Exception e){
            LOG.error("{} : Error while updating user", correlationId);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }finally {
            LOG.info("{} : Done updating user", correlationId);
        }
    }

    public ResponseEntity delete(long id, String correlationId){
        try {
            LOG.info("{} : Start deleting user", correlationId);

            User user = userRepository.findById(id).orElseThrow();

            if (user.getDeleted() == 1){
                LOG.info("{} : User with id " + user.getUserId() + " is not found");
                return ResponseEntity.notFound().build();
            }

            user.setDeleted(1);

            userRepository.save(user);
            userRepository.flush();
            LOG.info("{} : User was successfully deleted", correlationId);

            return ResponseEntity.ok().body(new ResponseResult(200, "User was successfully deleted", null));

        }catch (Exception e){
            LOG.error("{} : Error while deleting user", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }finally {
            LOG.info("{} : Finish deleting user", correlationId);
        }
    }
}

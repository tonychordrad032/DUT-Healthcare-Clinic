package com.codesurfers.duthealthcareclinic.appointment;

import com.codesurfers.duthealthcareclinic.utils.helpers.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    private static Logger LOG = (Logger) LoggerFactory.getLogger(Appointment.class);

    @Autowired
    private AppointmentRepository appointmentRepository;


    /**
     * Saves appointment to Database using JPA
     * @param appointment
     * @return
     */

    public ResponseEntity save(Appointment appointment, String correlationId) {
        try {
            LOG.info("{} : Start saving project {} ", correlationId, appointment);

            if (appointment == null){
                LOG.warn("{} : no content", correlationId);
                return ResponseEntity.noContent().build();
            }

            /**if (appointmentRepository.findByProjectName(project.getProjectName()) != null){
                LOG.warn("{} : Project already exists", correlationId);
                return ResponseEntity.status(409).body(new ResponseResult(409, "Project already exists", null));
            }

            AppUser appUser = appUserService.getUserByHttpRequest(request);
            project.setCreatedBy(appUser);
            project.setLastUpdatedBy(appUser);*/

            appointmentRepository.save(appointment);
            LOG.info("{} : appointment created successfully", correlationId);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(appointment.getAppointmentId()).toUri();

            return ResponseEntity.created(location).body(new ResponseResult(201, "Appointment was successfully created", null));
        }catch (Exception e){
            LOG.error("{} : Error while creating appointment {}", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }finally {
            LOG.info("{} : done creating appointment process", correlationId);
        }
    }

    /**
     * Returns all the appointments
     * @return
     */
    public List<Appointment> listAll(){
        return appointmentRepository.findAll()
                .stream()
                .filter(assetLocation -> assetLocation.getDeleted() == 0)
                .collect(Collectors.toList());
    }

    /**
     * Update appointment
     * @return
     */

    public ResponseEntity update(Appointment appointment, String correlationId){
        try {
            LOG.info("{} : Start Updating Appointment {} -> ", correlationId, appointment.toString());

            if (appointment == null){
                throw new Exception("The appointment is not found");
            }

            Appointment _appontment = appointmentRepository.findById(appointment.getAppointmentId()).orElseThrow(() -> new Exception("The appointment is not found"));

            //BusinessClient businessClient = businessClientRepository.findById(project.getBusinessClient().getClientId()).orElseThrow(() -> new Exception("Client not found."));

            //_appontment.setStudentNumber(appointment.getStudentNumber());
            _appontment.setStatus(appointment.getStatus());
            //_appontment.setQualification(appointment.getQualification());
            _appontment.setAppointmentDate(appointment.getAppointmentDate());


            appointmentRepository.save(_appontment);
            appointmentRepository.flush();

            return  ResponseEntity.ok().body(new ResponseResult(200, "appointment was successfully updated", appointment));

        }catch (Exception e){
            LOG.error("{} : Error while updating appointment {}", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400,e.getMessage(), null));
        }finally {
            LOG.info("{} : done updating appointment process {}", correlationId, appointment);
        }
    }

    /**
     * delete appointment
     * @return
     */

    public ResponseEntity delete(long id, String correlationId){
        try {
            LOG.info("{} : Start deleting appointment", correlationId);
            Appointment appointment = appointmentRepository.findById(id).orElseThrow(()-> new Exception("Appointment not found"));

            if (appointment.getDeleted() == 1) {
                throw new Exception("Appointment with id "+id+ " not found");
            }

            appointment.setDeleted(1);

            /**AppUser appUser = appUserService.getUserByHttpRequest(request);
            project.setLastUpdatedBy(appUser);*/

            appointmentRepository.save(appointment);
            LOG.info("{} : appointment was deleted", correlationId);

            return ResponseEntity.ok().body(new ResponseResult(200, "Appointment was successfully deleted", null));

        }catch (Exception e){
            LOG.error("{} : Error while deleting appointment {}", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }finally {
            LOG.info("{} : done deleting appointment process", correlationId);
        }
    }

    public ResponseEntity listAppointmentByUserId(long userId, String correlationId){
        try {
            LOG.info("{} : Start searching appointments", correlationId);
            /**List<Appointment> appointmentList = appointmentRepository.findAppointmentByUserId(userId);

            if (appointmentList.isEmpty()) {
                throw new Exception("Appointment with for user id "+userId+ " not found");
            }

            LOG.info("{} : appointment found", correlationId);*/

            return ResponseEntity.ok().body(new ResponseResult(200, "Appointment was found", null));

        }catch (Exception e){
            LOG.error("{} : Error while searching for appointments {}", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }finally {
            LOG.info("{} : done searching appointment process", correlationId);
        }
    }

    /**public List<Appointment> listAppointmentByUserId(){
        return appointmentRepository.findAppointmentByUserId(long id)
                .stream()
                .filter(assetLocation -> assetLocation.getDeleted() == 0)
                .collect(Collectors.toList());
    }*/
}

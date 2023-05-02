package com.codesurfers.duthealthcareclinic.appointment;

import com.codesurfers.duthealthcareclinic.time_slot.TimeSlot;
import com.codesurfers.duthealthcareclinic.time_slot.TimeSlotRepository;
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
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    private static Logger LOG = (Logger) LoggerFactory.getLogger(Appointment.class);

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;


    /**
     * Saves appointment to Database using JPA
     * @param appointment
     * @return
     */

    public ResponseEntity save(Appointment appointment, String correlationId) {
        try {
            LOG.info("{} : Start saving {} ", correlationId, appointment);

            if (appointment == null){
                LOG.warn("{} : no content", correlationId);
                return ResponseEntity.noContent().build();
            }

            LOG.info("{} : BEDORE SEARCHING {} " + appointment.getPatient().getUserId(), correlationId, appointment);



            /**List<Appointment> appointmentList = appointmentRepository.findAllById(Collections.singleton(appointment.getPatient().getUserId()))
                    .stream()
                    .filter(appointment1 -> appointment1.getDeleted() == 0 && Objects.equals(appointment1.getStatus(), "Open"))
                    .collect(Collectors.toList());*/

            List<Appointment> appointmentList = appointmentRepository.findOpenBookingsByUserId(appointment.getPatient().getUserId());

            LOG.info("{} : AFTER SEARCHING {} ", correlationId, appointmentList);

            if (!appointmentList.isEmpty()){
                List tempList = new ArrayList<>();
                System.out.println(appointmentList.size());
                for (int i = 0; i < appointmentList.size(); i++) {
                    if (Objects.equals(appointmentList.get(i).getStatus(), "Open")){
                        tempList.add(appointmentList.get(i));
                    }
                }
                System.out.println(tempList.size());
                if (tempList.size() > 0) {
                    LOG.warn("{} : You already have pending appointment", tempList);
                    return ResponseEntity.status(409).body(new ResponseResult(409, "You already have pending appointment", null));
                }
            }


            TimeSlot bookedTimeSlot = timeSlotRepository.findById(appointment.getAppointmentTime().getTimeSlotId()).orElseThrow();
            LOG.info("{} : SEARCHING TIME SLOT ", correlationId, bookedTimeSlot);
            if(bookedTimeSlot != null){
                if (bookedTimeSlot.isBooked() == true){
                    return ResponseEntity.status(409).body(new ResponseResult(409, "Time slot "+ appointment.getAppointmentTime().getTime() + " already booked", null));
                }
            }
            LOG.info("{} : AFTER SEARCHING TIME SLOT ", correlationId, bookedTimeSlot);


            bookedTimeSlot.setBooked(true);
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
            _appontment.setNotes(appointment.getNotes());
            //_appontment.setQualification(appointment.getQualification());
            //_appontment.setAppointmentDate(appointment.getAppointmentDate());


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
            LOG.info("{} : Start searching appointment by user id", correlationId);

            List<Appointment> appointmentList = appointmentRepository.findAll()
                    .stream()
                    .filter(appointment -> appointment.getDeleted() == 0 && Objects.equals(appointment.getPatient().getUserId(), userId))
                    .collect(Collectors.toList());

            if (appointmentList.isEmpty()) {
                return ResponseEntity.ok().body(new ResponseResult(200, "No appointment found", appointmentList));
            }

            LOG.info("{} : appointment found", correlationId);

            return ResponseEntity.ok().body(new ResponseResult(200, "Appointment was found", appointmentList));

        }catch (Exception e){
            LOG.error("{} : Error while searching for appointments by user id{}", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }finally {
            LOG.info("{} : done searching appointment by user id process", correlationId);
        }
    }

    public ResponseEntity findAppointmentByDay(String day, String correlationId){
        try {
            LOG.info("{} : Start searching appointment", correlationId);

            if (day == null) {
                LOG.warn("{} : no content", correlationId);
                return ResponseEntity.noContent().build();
            }

            List<Appointment> appointmentList = appointmentRepository.findAll()
                    .stream()
                    .filter(appointment -> appointment.getDeleted() == 0 && (Objects.equals(appointment.getAppointmentTime().getDay().getAppointmentDayName(), day) && Objects.equals(appointment.getStatus(), "Open")))
                    .collect(Collectors.toList());


            if (appointmentList.isEmpty()) {
                //LOG.warn("{} : time slot already exist");
                return ResponseEntity.ok().body(new ResponseResult(200, "There are no appointments available for " + day, appointmentList));

            }

            return ResponseEntity.ok().body(new ResponseResult(200, "There are "+appointmentList.size()+ " appointments available for "+ day, appointmentList));
        }catch (Exception e){
            LOG.error("{} : Error while fetching appointment", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }finally {
            LOG.info("{} : Done fetching appointments", correlationId);
        }

    }
}

package com.codesurfers.duthealthcareclinic.appointment_day;
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
public class AppointmentDayService {

    private static Logger LOG = LoggerFactory.getLogger(AppointmentDayService.class);

    @Autowired
    private AppointmentDayRepository appointmentDayRepository;

    public ResponseEntity save(AppointmentDay appointmentDay, String correlationId){
        try {
            LOG.info("{} : Start adding appointment day", correlationId);

            if (appointmentDay == null) {
                LOG.warn("{} : no content", correlationId);
                return ResponseEntity.noContent().build();
            }

            AppointmentDay appointmentDay1 = appointmentDayRepository.findByAppointmentDayName(appointmentDay.getAppointmentDayName());

            if (appointmentDay1 != null) {
                LOG.warn("{} : appointment day already exist");
                return ResponseEntity.status(409).body(new ResponseResult(409, "Day Already exist", null));

            }

            appointmentDayRepository.save(appointmentDay);
            appointmentDayRepository.flush();

            LOG.info("{} : Appointment day was added successfully", correlationId);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(appointmentDay.getAppointmentDayId()).toUri();

            return ResponseEntity.created(location).
                    body(new ResponseResult(201, "Appointment day was added successfully", null));
        }catch (Exception e){
            LOG.error("{} : Error while adding appointment day", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }finally {
            LOG.info("{} : Done adding appointment day", correlationId);
        }

    }

    /**
     * Returns all the appointmentDay
     * @return
     */
    public List<AppointmentDay> listAll() {

        return appointmentDayRepository.findAll()
                .stream()
                .filter(appointmentDay -> appointmentDay.getDeleted() == 0)
                .collect(Collectors.toList());
    }

}

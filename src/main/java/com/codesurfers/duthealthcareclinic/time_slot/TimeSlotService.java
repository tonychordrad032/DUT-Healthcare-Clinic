package com.codesurfers.duthealthcareclinic.time_slot;

import com.codesurfers.duthealthcareclinic.appointment_day.AppointmentDay;
import com.codesurfers.duthealthcareclinic.appointment_day.AppointmentDayRepository;
import com.codesurfers.duthealthcareclinic.user.User;
import com.codesurfers.duthealthcareclinic.utils.helpers.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TimeSlotService {
    private static Logger LOG = LoggerFactory.getLogger(TimeSlotService.class);

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private AppointmentDayRepository appointmentDayRepository;

    public ResponseEntity save(TimeSlot timeSlot, String correlationId){
        try {
            LOG.info("{} : Start adding time slot", correlationId);

            if (timeSlot == null) {
                LOG.warn("{} : no content", correlationId);
                return ResponseEntity.noContent().build();
            }

            TimeSlot timeSlot1 = timeSlotRepository.findByTime(timeSlot.getTime());

            if (timeSlot1 != null) {
                LOG.warn("{} : time slot already exist");
                return ResponseEntity.status(409).body(new ResponseResult(409, "Time slot Already exist", null));

            }

            timeSlotRepository.save(timeSlot);
            timeSlotRepository.flush();

            LOG.info("{} : Time slot was added successfully", correlationId);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(timeSlot.getTimeSlotId()).toUri();

            return ResponseEntity.created(location).
                    body(new ResponseResult(201, "Time slot was added successfully", null));
        }catch (Exception e){
            LOG.error("{} : Error while adding time slot", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }finally {
            LOG.info("{} : Done adding time slot", correlationId);
        }

    }

    /**
     * Returns all the time slot
     * @return
     */
    public List<TimeSlot> listAll() {

        return timeSlotRepository.findAll()
                .stream()
                .filter(timeSlot -> timeSlot.getDeleted() == 0)
                .collect(Collectors.toList());
    }

    public ResponseEntity findTimeSlotByDay(String day, String correlationId){
        try {
            LOG.info("{} : Start searching time slot", correlationId);

            if (day == null) {
                LOG.warn("{} : no content", correlationId);
                return ResponseEntity.noContent().build();
            }





            List<TimeSlot> timeSlotList = timeSlotRepository.findAll()
                    .stream()
                    .filter(timeSlot -> timeSlot.isBooked() == false && (Objects.equals(timeSlot.getDay().getAppointmentDayName(), day) && Objects.equals(timeSlot.getDay().isBooked(), false)))
                    .toList();

            /**List<TimeSlot> availableTimeSlotList = null;
            for (int i = 0; i < timeSlotList.size(); i++) {
                if (timeSlotList.get(i).isBooked() == true){
                    availableTimeSlotList.add(timeSlotList.get(i));
                }
            }*/


            if (timeSlotList.isEmpty()) {
                //LOG.warn("{} : time slot already exist");
                return ResponseEntity.status(409).body(new ResponseResult(409, "There are no time slot available for today", null));

            }

            return ResponseEntity.ok().body(new ResponseResult(200, "There are "+timeSlotList.size()+ " available for "+ day, timeSlotList));
        }catch (Exception e){
            LOG.error("{} : Error while fetching time slot", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }finally {
            LOG.info("{} : Done fetching time slot", correlationId);
        }

    }


}

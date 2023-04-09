package com.codesurfers.duthealthcareclinic.clinic;


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
public class ClinicService {
    private static Logger LOG = LoggerFactory.getLogger(Clinic.class);

    @Autowired
    private ClinicRepository clinicRepository;

    /**
     * Saves clinic to Database using JPA
     * @param clinic
     * @return
     */
    public ResponseEntity<ResponseResult> save(Clinic clinic, String correlationId) {
        try {
            LOG.info("{} : start adding clinic process {}", correlationId, clinic);
            if (clinic == null || clinic.getClinicName().equals("")) {
                LOG.warn("{} : no content", correlationId);
                return ResponseEntity.noContent().build();
            }

            if(clinicRepository.findByClinicName(clinic.getClinicName()) != null){
                LOG.warn("{} : Clinic already exists", correlationId);
                return ResponseEntity.status(409).body(new ResponseResult(409, "Clinic already exists", null));
            }

            clinicRepository.save(clinic);
            clinicRepository.flush();
            LOG.info("{} : clinic added successfully", correlationId);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clinic.getClinicId()).toUri();

            return ResponseEntity.created(location).body(new ResponseResult(201, "Clinic was successfully added", null));
        } catch (Exception e) {
            LOG.error("{} : Error while adding clinic {}", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }
        finally {
            LOG.info("{} : done adding clinic process", correlationId);
        }
    }

    /**
     * Returns all the clinics
     * @return
     */
    public List<Clinic> listAll() {

        return clinicRepository.findAll()
                .stream()
                .filter(clinic -> clinic.getDeleted() == 0)
                .collect(Collectors.toList());
    }

}

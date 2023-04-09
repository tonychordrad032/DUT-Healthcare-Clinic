package com.codesurfers.duthealthcareclinic.clinic;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    Clinic findByClinicName(String clinicName);
}

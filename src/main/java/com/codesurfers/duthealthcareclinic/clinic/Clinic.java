package com.codesurfers.duthealthcareclinic.clinic;

import javax.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "tbl_clinics")
@Data
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clinicId;
    private  String clinicName;
    private  String clinicDescription;
    private  String clinicCampus;
    private int deleted = 0;
    private String latitude;
    private String longitude;
    private LocalDateTime addedDate;

}

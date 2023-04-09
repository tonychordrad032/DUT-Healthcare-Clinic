package com.codesurfers.duthealthcareclinic.feedback;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "tbl_feedbacks")
@Data
public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long feedbackId;

    private String name;
    private String surname;
    private String feedBackType;
    private String message;
    private int deleted = 0;
}

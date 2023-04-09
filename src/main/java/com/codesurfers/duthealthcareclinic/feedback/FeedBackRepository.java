package com.codesurfers.duthealthcareclinic.feedback;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackRepository extends JpaRepository<FeedBack, Long> {
    //FeedBack findByType(String feedBackType);
}

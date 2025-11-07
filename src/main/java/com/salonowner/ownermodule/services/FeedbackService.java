package com.salonowner.ownermodule.services;


import com.salonowner.ownermodule.Entity.Feedback;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {

    List<Feedback> findAll();

    Optional<Feedback> findById(Long id);

    Feedback save(Feedback feedback);

    Feedback saveResponse(Long id, String response);
}

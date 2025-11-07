package com.salonowner.ownermodule.services.impl;

import com.salonowner.ownermodule.Entity.Feedback;
import com.salonowner.ownermodule.repository.FeedbackRepository;
import com.salonowner.ownermodule.services.FeedbackService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository repo;

    public FeedbackServiceImpl(FeedbackRepository repo) {
        this.repo = repo;
    }

    public List<Feedback> findAll() {
        return repo.findAll();
    }

    public Optional<Feedback> findById(Long id) {
        return repo.findById(id);
    }

    public Feedback save(Feedback f) {
        return repo.save(f);
    }

    public Feedback saveResponse(Long id, String response) {
        Feedback f = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Feedback not found: " + id));
        f.setResponse(response);
        f.setRespondedAt(LocalDateTime.now());
        return repo.save(f);
    }
}

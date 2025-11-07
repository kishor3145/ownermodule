package com.salonowner.ownermodule.controller;

import com.salonowner.ownermodule.Entity.Feedback;
import com.salonowner.ownermodule.services.impl.FeedbackServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "http://localhost:3000") // allow React dev server
public class FeedbackController {

    private final FeedbackServiceImpl service;

    public FeedbackController(FeedbackServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<Feedback> listAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> get(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/response")
    public ResponseEntity<Feedback> respond(@PathVariable Long id, @RequestBody ResponseRequest req) {
        if (req == null || req.getResponse() == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Feedback updated = service.saveResponse(id, req.getResponse());
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    public static class ResponseRequest {
        private String response;
        public String getResponse() { return response; }
        public void setResponse(String response) { this.response = response; }
    }
}

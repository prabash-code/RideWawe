package edu.icet.controller;

import edu.icet.model.dto.request.FeedbackRequest;
import edu.icet.model.dto.response.FeedbackResponse;
import edu.icet.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://127.0.0.1:5173"})
@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackResponse> createFeedback(@RequestBody FeedbackRequest feedbackRequest) {
        FeedbackResponse created = feedbackService.createNewFeedback(feedbackRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<FeedbackResponse>> getFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }
}

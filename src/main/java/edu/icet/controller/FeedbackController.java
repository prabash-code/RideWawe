package edu.icet.controller;

import edu.icet.model.dto.request.FeedbackRequest;
import edu.icet.model.dto.response.FeedbackResponse;
import edu.icet.model.entity.FeedbackEntity;
import edu.icet.repository.FeedbackRepository;
import edu.icet.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;


    @PostMapping
public FeedbackResponse createFeedback(@RequestBody FeedbackRequest feedbackRequest){
      return feedbackService.createNewFeedback(feedbackRequest);

    }

    @GetMapping
    public List<FeedbackResponse> getFeedbacks(){
        return feedbackService.getAllFeedbacks();
    }
}

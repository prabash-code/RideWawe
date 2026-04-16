package edu.icet.service;

import edu.icet.model.dto.request.FeedbackRequest;
import edu.icet.model.dto.response.FeedbackResponse;
import edu.icet.repository.FeedbackRepository;

import java.util.List;

public interface FeedbackService {
   FeedbackResponse createNewFeedback(FeedbackRequest feedbackRequest);

    List<FeedbackResponse> getAllFeedbacks();
}

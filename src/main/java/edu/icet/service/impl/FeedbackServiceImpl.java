package edu.icet.service.impl;

import edu.icet.model.dto.request.FeedbackRequest;
import edu.icet.model.dto.response.FeedbackResponse;
import edu.icet.model.entity.FeedbackEntity;
import edu.icet.repository.FeedbackRepository;
import edu.icet.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Override
    public FeedbackResponse createNewFeedback(FeedbackRequest feedbackRequest) {
        FeedbackEntity feedbackEntity = new FeedbackEntity();

        feedbackEntity.setName(feedbackRequest.getName());
        feedbackEntity.setEmail(feedbackRequest.getEmail());

        // Default to 5 stars if rating wasn't changed or is <= 0
        int rating = (feedbackRequest.getRatings() > 0) ? feedbackRequest.getRatings() : 5;
        feedbackEntity.setRatings(rating);

        feedbackEntity.setReview(feedbackRequest.getReview());
        if (feedbackEntity.getCreatedAt() == null) {
            feedbackEntity.setCreatedAt(LocalDateTime.now());
        }

        FeedbackEntity savedEntity = feedbackRepository.save(feedbackEntity);

        return new FeedbackResponse(
                savedEntity.getFeedbackId(),
                savedEntity.getName(),
                savedEntity.getEmail(),
                savedEntity.getRatings(),
                savedEntity.getReview(),
                savedEntity.getCreatedAt() != null ? savedEntity.getCreatedAt() : LocalDateTime.now()
        );
    }

    @Override
    public List<FeedbackResponse> getAllFeedbacks() {
        List<FeedbackEntity> all = feedbackRepository.findAll();
        List<FeedbackResponse> responses = new ArrayList<>();

        for (FeedbackEntity fed : all) {
            responses.add(new FeedbackResponse(
                    fed.getFeedbackId(),
                    fed.getName(),
                    fed.getEmail(),
                    fed.getRatings(),
                    fed.getReview(),
                    fed.getCreatedAt()
            ));
        }

        return responses;
    }
}

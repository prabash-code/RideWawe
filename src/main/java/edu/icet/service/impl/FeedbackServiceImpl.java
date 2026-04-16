package edu.icet.service.impl;

import edu.icet.model.dto.request.FeedbackRequest;
import edu.icet.model.dto.response.FeedbackResponse;
import edu.icet.model.entity.FeedbackEntity;
import edu.icet.repository.FeedbackRepository;
import edu.icet.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    @Override
    public FeedbackResponse createNewFeedback(FeedbackRequest feedbackRequest) {
        FeedbackEntity feedbackEntity =new FeedbackEntity();

        feedbackEntity.setName(feedbackRequest.getName());
        feedbackEntity.setEmail(feedbackRequest.getEmail());
        feedbackEntity.setRatings(feedbackRequest.getRatings());
        feedbackEntity.setReview(feedbackRequest.getReview());

        feedbackRepository.save(feedbackEntity);

        return new FeedbackResponse(
                feedbackEntity.getFeedbackId(),
                feedbackEntity.getName(),
                feedbackEntity.getEmail(),
                feedbackEntity.getRatings(),
                feedbackEntity.getReview(),
                feedbackEntity.getCreatedAt()
        );
    }

    @Override
    public List<FeedbackResponse> getAllFeedbacks() {
        List<FeedbackEntity> all = feedbackRepository.findAll();
        List<FeedbackResponse> responses=new ArrayList<>();

        for(FeedbackEntity fed:all){
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

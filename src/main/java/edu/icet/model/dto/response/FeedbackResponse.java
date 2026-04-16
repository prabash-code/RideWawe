package edu.icet.model.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackResponse {
    private Long id;
    private String name;
    private String email;
    private int ratings;
    private String review;
    private LocalDateTime createdAt;
}

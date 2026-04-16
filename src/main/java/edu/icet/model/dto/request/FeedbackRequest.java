package edu.icet.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequest {
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Email is required")
    private String email;
    @NotNull(message = "ratings is required")
    private int ratings;
    @NotNull(message = "Review is required")
    private String review;

}

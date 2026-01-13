package com.project.fitness.dto;

import com.project.fitness.model.Activity;
import com.project.fitness.model.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponse {
    private String id;
    private String userId ;
    private ActivityType type ;
    private Map<String , Object> additionalMetrics ;
    private Integer duration;
    private Integer calorieBurned;
    private LocalDateTime startTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ActivityResponse(Activity savedActivity) {
        this.id = savedActivity.getId();
        this.userId = savedActivity.getUser().getId();
        this.type = savedActivity.getType();
        this.additionalMetrics = savedActivity.getAdditionalMetrics();
        this.duration = savedActivity.getDuration();
        this.calorieBurned = savedActivity.getCalorieBurned();
        this.startTime = savedActivity.getStartTime();
        this.createdAt = savedActivity.getCreatedAt();
        this.updatedAt = savedActivity.getUpdatedAt();
    }
}

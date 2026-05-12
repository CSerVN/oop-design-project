package com.group3.strategy;

import com.group3.model.WorkoutLog;

public interface NextSetRecommendationStrategy {
	RecommendationResult calculateNextSet(WorkoutLog currentLog);
}

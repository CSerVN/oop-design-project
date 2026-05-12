package com.group3.controller;

import com.group3.model.User;
import com.group3.model.WorkoutLog;
import com.group3.strategy.LoseFatStrategy;
import com.group3.strategy.MuscleGainStrategy;
import com.group3.strategy.NextSetRecommendationStrategy;
import com.group3.strategy.NoWeightStrategy;
import com.group3.strategy.RecommendationResult;

public class WorkoutHandling {

	private NextSetRecommendationStrategy currentStrategy;

	public RecommendationResult calculateNextSet(WorkoutLog currentLog) {
		if (this.currentStrategy == null) {
			throw new IllegalStateException("Chưa thiết lập chiến lược tập luyện (Strategy)!");
		}
		return this.currentStrategy.calculateNextSet(currentLog);
	}

	public void setGoal(User user) {
		if (user == null || user.getGoal() == null) {
			this.currentStrategy = new NoWeightStrategy();
			return;
		}

		switch (user.getGoal()) {
		case MUSCLE_GAIN:
			this.currentStrategy = new MuscleGainStrategy();
			break;
		case LOSE_FAT:
			this.currentStrategy = new LoseFatStrategy();
			break;
		case MAINTENANCE:
		default:
			this.currentStrategy = new NoWeightStrategy();
			break;
		}
	}

}
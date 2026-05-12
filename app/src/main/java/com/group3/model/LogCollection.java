package com.group3.model;

import java.util.ArrayList;
import java.util.List;

public class LogCollection {
	private List<WorkoutLog> workoutLogs;
	private List<NutritionLog> nutritionLogs;

	public LogCollection(List<WorkoutLog> workoutLogs, List<NutritionLog> nutritionLogs) {
		this.workoutLogs = workoutLogs != null ? workoutLogs : new ArrayList<>();
		this.nutritionLogs = nutritionLogs != null ? nutritionLogs : new ArrayList<>();
	}

	public List<WorkoutLog> getWorkoutLogs() {
		return workoutLogs;
	}

	public List<NutritionLog> getNutritionLogs() {
		return nutritionLogs;
	}
}
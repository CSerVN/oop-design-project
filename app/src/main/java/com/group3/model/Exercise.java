package com.group3.model;

public class Exercise {
	private int exerciseID;
	private String exerciseName;
	private ExerciseCategory category;
	private TrackingType trackingType;
	private String targetMuscle;

	public Exercise(int exerciseID, String exerciseName, ExerciseCategory category, TrackingType trackingType,
			String targetMuscle) {
		this.exerciseID = exerciseID;
		this.exerciseName = exerciseName;
		this.category = category;
		this.trackingType = trackingType;
		this.targetMuscle = targetMuscle;
	}

	public int getExerciseID() {
		return exerciseID;
	}

	public void setExerciseID(int exerciseID) {
		this.exerciseID = exerciseID;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public ExerciseCategory getCategory() {
		return category;
	}

	public void setCategory(ExerciseCategory category) {
		this.category = category;
	}

	public TrackingType getTrackingType() {
		return trackingType;
	}

	public void setTrackingType(TrackingType trackingType) {
		this.trackingType = trackingType;
	}

	public String getTargetMuscle() {
		return targetMuscle;
	}

	public void setTargetMuscle(String targetMuscle) {
		this.targetMuscle = targetMuscle;
	}

	@Override
	public String toString() {
		return String.format("[%d], %s | %s | %s | Target: %s", exerciseID, exerciseName, category, trackingType,
				targetMuscle);
	}
}

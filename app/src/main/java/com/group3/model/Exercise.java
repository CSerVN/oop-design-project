package com.group3.model;

public class Exercise {
	private int exerciseID;
	private String exerciseName;
	private ExerciseCategory category;
	private TrackingType trackingType;
	private String targetMuscle;

	public Exercise(int exerciseID, String exerciseName, ExerciseCategory category,
			TrackingType trackingType, String targetMuscle) {
		this.exerciseID = exerciseID;
		this.exerciseName = exerciseName;
		this.category = category;
		this.trackingType = trackingType;
		this.targetMuscle = targetMuscle;
	}

	public int getExerciseID() {
		return exerciseID;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public ExerciseCategory getCategory() {
		return category;
	}

	public TrackingType getTrackingType() {
		return trackingType;
	}

	public String getTargetMuscle() {
		return targetMuscle;
	}

}

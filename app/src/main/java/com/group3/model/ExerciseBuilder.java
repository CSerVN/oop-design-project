package com.group3.model;

public class ExerciseBuilder {
	private int exerciseID;
	private String exerciseName;
	private ExerciseCategory category;
	private TrackingType trackingType;
	private String targetMuscle;

	public ExerciseBuilder setExerciseID(int exerciseID) {
		this.exerciseID = exerciseID;
		return this;
	}

	public ExerciseBuilder setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
		return this;
	}

	public ExerciseBuilder setCategory(ExerciseCategory category) {
		this.category = category;
		return this;
	}

	public ExerciseBuilder setTrackingType(TrackingType trackingType) {
		this.trackingType = trackingType;
		return this;
	}

	public ExerciseBuilder setTargetMuscle(String targetMuscle) {
		this.targetMuscle = targetMuscle;
		return this;
	}

	public Exercise build() {
		if (this.exerciseID == -1 || this.exerciseName == null || this.exerciseName.isEmpty()) {
			throw new IllegalArgumentException("Don't have that exercise data!!");
		}
		return new Exercise(this.exerciseID, this.exerciseName, this.category, this.trackingType, this.targetMuscle);
	}
}

package com.group3.model;

public class Exercise {
	private final int exerciseID;
	private final String exerciseName;
	private final ExerciseCategory category;
	private final TrackingType trackingType;
	private final String targetMuscle;

	private Exercise(ExerciseBuilder builder) {
	    this.exerciseID = builder.exerciseID;
	    this.exerciseName = builder.exerciseName;
	    this.category = builder.category;
	    this.trackingType = builder.trackingType;
	    this.targetMuscle = builder.targetMuscle;
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
	@Override
	public String toString() {
		return String.format("[%d], %s | %s | %s | Target: %s", exerciseID, exerciseName, category, trackingType,
				targetMuscle);
	}
	public static class ExerciseBuilder {
		private int exerciseID = -1;
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
			if (this.exerciseID == -1 || this.exerciseName == null || this.exerciseName.isBlank()) {
				throw new IllegalStateException("Exercise id and name are required!!");
			}
			if(this.category == null) 
				throw new IllegalStateException("Exercise category is required");
			if (trackingType == null)
	            throw new IllegalStateException("Exercise tracking type is required.");
			return new Exercise(this);
		}
	}
}

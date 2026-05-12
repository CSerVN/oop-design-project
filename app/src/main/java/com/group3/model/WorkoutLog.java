package com.group3.model;

import java.time.LocalDateTime;

public class WorkoutLog {
	private final int logID;
	private final LocalDateTime date;
	private final Exercise exercise;
	// Wrapper fields
	private final Double weight;
	private final Integer reps;
	private final Double distance;
	private final Double time;
	
	private WorkoutLog(WorkoutLogBuilder builder) {
		this.logID = builder.logID;
		this.date = builder.date;
		this.exercise = builder.exercise;
		this.weight = builder.weight;
		this.reps = builder.reps;
		this.distance = builder.distance;
		this.time = builder.time;
	}
	
	public int getLogID() {
		return logID;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public Double getWeight() {
		return weight;
	}
	public Integer getReps() {
		return reps;
	}
	public Double getDistance() {
		return distance;
	}
	public Double getTime() {
		return time;
	}
	public static class WorkoutLogBuilder {
		private int logID = -1;
		private LocalDateTime date;
		private Exercise exercise;
		// Default assignment operator
		private Double weight = null;
		private Integer reps = null;
		private Double distance = null;
		private Double time = null;

		public WorkoutLogBuilder setLogID(int logID) {
			this.logID = logID;
			return this;
		}

		public WorkoutLogBuilder setDate(LocalDateTime date) {
			this.date = date;
			return this;
		}

		public WorkoutLogBuilder setExercise(Exercise exercise) {
			this.exercise = exercise;
			return this;
		}

		public WorkoutLogBuilder setWeight(Double weight) {
			this.weight = weight;
			return this;
		}

		public WorkoutLogBuilder setReps(Integer reps) {
			this.reps = reps;
			return this;
		}

		public WorkoutLogBuilder setDistance(Double distance) {
			this.distance = distance;
			return this;
		}

		public WorkoutLogBuilder setTime(Double time) {
			this.time = time;
			return this;
		}

		public WorkoutLog build() {
			if (this.logID == -1)
				throw new IllegalStateException("Cannot create log: log id required!");
			if (this.date == null)
				throw new IllegalStateException("Cannot create log: date required!");
			if (this.exercise == null)
				throw new IllegalStateException("Cannot create log: date required!");
			return new WorkoutLog(this);
		}
	}
}

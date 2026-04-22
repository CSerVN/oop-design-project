package com.group3.model;

import java.time.LocalDateTime;

public class WorkoutLog {
	private final int logID;
	private final LocalDateTime date;
	private final Exercise exercise;
	private final double weight;
	private final int reps;
	private final double distance;
	private final double time;
	
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

	public double getWeight() {
		return weight;
	}
	public int getReps() {
		return reps;
	}
	public double getDistance() {
		return distance;
	}
	public double getTime() {
		return time;
	}
	public static class WorkoutLogBuilder {
		private int logID = -1;
		private LocalDateTime date;
		private Exercise exercise;
		private double weight;
		private int reps;
		private double distance;
		private double time;

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

		public WorkoutLogBuilder setWeight(double weight) {
			this.weight = weight;
			return this;
		}

		public WorkoutLogBuilder setReps(int reps) {
			this.reps = reps;
			return this;
		}

		public WorkoutLogBuilder setDistance(double distance) {
			this.distance = distance;
			return this;
		}

		public WorkoutLogBuilder setTime(double time) {
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

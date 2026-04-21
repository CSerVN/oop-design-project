package com.group3.model;

import java.time.LocalDateTime;

public class WorkoutLog {
	private int logID;
	private LocalDateTime date;
	private Exercise exercise;
	private double weight;
	private int reps;
	private double distance;
	private double time;
	
	public WorkoutLog(int logID, LocalDateTime date, Exercise exercise, double weight, int reps, double distance,
			double time) {
		this.logID = logID;
		this.date = date;
		this.exercise = exercise;
		this.weight = weight;
		this.reps = reps;
		this.distance = distance;
		this.time = time;
	}
	public int getLogID() {
		return logID;
	}
	public void setLogID(int logID) {
		this.logID = logID;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public Exercise getExercise() {
		return exercise;
	}
	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getReps() {
		return reps;
	}
	public void setReps(int reps) {
		this.reps = reps;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	
}

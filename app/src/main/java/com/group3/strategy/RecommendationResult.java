package com.group3.strategy;

public class RecommendationResult {
	private Double suggestedWeight;
	private Integer suggestedReps;
	private Double suggestedDistance;
	private Double suggestedTime;
	private String message;

	public RecommendationResult(Double suggestedWeight, Integer suggestedReps, Double suggestedDistance,
			Double suggestedTime, String message) {
		this.suggestedWeight = suggestedWeight;
		this.suggestedReps = suggestedReps;
		this.suggestedDistance = suggestedDistance;
		this.suggestedTime = suggestedTime;
		this.message = message;
	}

	public Double getSuggestedWeight() {
		return suggestedWeight;
	}

	public Integer getSuggestedReps() {
		return suggestedReps;
	}

	public Double getSuggestedDistance() {
		return suggestedDistance;
	}

	public Double getSuggestedTime() {
		return suggestedTime;
	}

	public String getMessage() {
		return message;
	}

}

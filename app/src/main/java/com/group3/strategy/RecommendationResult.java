package com.group3.strategy;

public class RecommendationResult {
	private double suggestedWeight;
	private double suggestedReps;
	private double suggestedDistance;
	private double suggestedTime;
	private String message;
	public RecommendationResult(double suggestedWeight, double suggestedReps, double suggestedDistance,
			double suggestedTime, String message) {
		this.suggestedWeight = suggestedWeight;
		this.suggestedReps = suggestedReps;
		this.suggestedDistance = suggestedDistance;
		this.suggestedTime = suggestedTime;
		this.message = message;
	}
	
}

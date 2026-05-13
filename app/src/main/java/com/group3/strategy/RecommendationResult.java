package com.group3.strategy;

import java.time.format.DateTimeFormatter;

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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		if (suggestedWeight != null)
			sb.append("Mức tạ: ").append(suggestedWeight).append(" kg\n");
		if (suggestedReps != null)
			sb.append("Số hiệp: ").append(suggestedReps).append("\n");
		if (suggestedDistance != null)
			sb.append("Quãng đường: ").append(suggestedDistance).append(" km\n");
		if (suggestedTime != null)
			sb.append("Thời gian: ").append(suggestedTime).append(" phút\n");
		return sb.toString();
	}
}

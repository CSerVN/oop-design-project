package com.group3.model;

public class NutritionLog {
	private int productID;
	private String productName;
	private double energy;
	private double protein;
	private double fat;
	public NutritionLog(int productID, String productName, double energy, double protein, double fat) {
		this.productID = productID;
		this.productName = productName;
		this.energy = energy;
		this.protein = protein;
		this.fat = fat;
	}
	
}

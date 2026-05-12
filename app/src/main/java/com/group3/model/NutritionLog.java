package com.group3.model;

public class NutritionLog {
	private final int productID;
	private final String productName;
	// Wrapper fields
	private final Double energy;
	private final Double protein;
	private final Double fat;
	private final Double carbohydrates;
	private NutritionLog(NutritionLogBuilder builder) {
		this.productID = builder.productID;
		this.productName = builder.productName;
		this.energy = builder.energy;
		this.protein = builder.protein;
		this.fat = builder.fat;
		this.carbohydrates = builder.carbohydrates;
	}
	public int getProductID() {
		return productID;
	}
	public String getProductName() {
		return productName;
	}
	public Double getEnergy() {
		return energy;
	}
	public Double getProtein() {
		return protein;
	}
	public Double getFat() {
		return fat;
	}
	public Double getCarbohydrates() {
		return carbohydrates;
	}

	public static class NutritionLogBuilder {
		private int productID = -1;
		private String productName;
		// Default assignment operator
		private Double energy = null;
		private Double protein = null;
		private Double fat = null;
		private Double carbohydrates = null;
		
		public NutritionLogBuilder setProductID(int productID) {
			this.productID = productID;
			return this;
		}
		public NutritionLogBuilder setProductName(String productName) {
			this.productName = productName;
			return this;
		}
		public NutritionLogBuilder setEnergy(Double energy) {
			this.energy = energy;
			return this;
		}
		public NutritionLogBuilder setProtein(Double protein) {
			this.protein = protein;
			return this;
		}
		public NutritionLogBuilder setFat(Double fat) {
			this.fat = fat;
			return this;
		}
		public NutritionLogBuilder carbohydrates(Double carbohydrates) {
			this.carbohydrates = carbohydrates;
			return this;
		}
		public NutritionLog build() {
			if(this.productID == -1 || this.productName == null || this.productName.isBlank()) {
				throw new IllegalStateException("product id and name are required!");
			}
			return new NutritionLog(this);
		}
	}
}

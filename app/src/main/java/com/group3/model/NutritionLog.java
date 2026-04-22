package com.group3.model;

public class NutritionLog {
	private final int productID;
	private final String productName;
	private final double energy;
	private final double protein;
	private final double fat;
	private NutritionLog(NutritionLogBuilder builder) {
		this.productID = builder.productID;
		this.productName = builder.productName;
		this.energy = builder.energy;
		this.protein = builder.protein;
		this.fat = builder.fat;
	}
	public int getProductID() {
		return productID;
	}
	public String getProductName() {
		return productName;
	}
	public double getEnergy() {
		return energy;
	}
	public double getProtein() {
		return protein;
	}
	public double getFat() {
		return fat;
	}
	public static class NutritionLogBuilder {
		private int productID = -1;
		private String productName;
		private double energy;
		private double protein;
		private double fat;
		
		public NutritionLogBuilder setProductID(int productID) {
			this.productID = productID;
			return this;
		}
		public NutritionLogBuilder setProductName(String productName) {
			this.productName = productName;
			return this;
		}
		public NutritionLogBuilder setEnergy(double energy) {
			this.energy = energy;
			return this;
		}
		public NutritionLogBuilder setProtein(double protein) {
			this.protein = protein;
			return this;
		}
		public NutritionLogBuilder setFat(double fat) {
			this.fat = fat;
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

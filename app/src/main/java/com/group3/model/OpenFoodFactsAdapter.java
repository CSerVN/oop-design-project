package com.group3.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.group3.api.OpenFoodFactsAPI;
import com.google.gson.JsonArray;

public class OpenFoodFactsAdapter implements INutrition {

	private OpenFoodFactsAPI api;

	public OpenFoodFactsAdapter() {
		this.api = new OpenFoodFactsAPI();
	}

	@Override
	public NutritionLog getNutritionInfo(String productName) {
		String jsonResponse = api.fetchProductJsonByName(productName);

		// Check null
		if (jsonResponse == null || jsonResponse.isBlank() || jsonResponse.contains("\"count\":0")) {
			System.out.println("Không tìm thấy sản phẩm này trên hệ thống Open Food Facts.");
			return null;
		}

		try {
			// parse String to Object
			JsonObject root = JsonParser.parseString(jsonResponse).getAsJsonObject();

			JsonArray products = root.getAsJsonArray("products");
			if (products == null || products.size() == 0) {
				return null;
			}

			// Get first one
			JsonObject firstProduct = products.get(0).getAsJsonObject();

			// "nutriments" compound nutrition index
			JsonObject nutriments = firstProduct.getAsJsonObject("nutriments");

			String realName = firstProduct.has("product_name") ? firstProduct.get("product_name").getAsString()
					: productName;

			// Invoke builder pattern to create new Object
			return new NutritionLog.Builder().setProductID((int) (System.currentTimeMillis() % 100000)) // Tạm sinh ID
																										// tự động
					.setProductName(realName).setEnergy(getSafeDouble(nutriments, "energy-kcal_100g"))
					.setProtein(getSafeDouble(nutriments, "proteins_100g"))
					.setFat(getSafeDouble(nutriments, "fat_100g"))
					.setCarbohydrates(getSafeDouble(nutriments, "carbohydrates_100g")).build();

		} catch (Exception e) {
			System.err.println("Lỗi khi phân tích dữ liệu JSON từ API! Dữ liệu có thể bị sai định dạng.");
			e.printStackTrace();
			return null;
		}
	}

	// json supporter method (the reason I used Wrapper fields)
	private Double getSafeDouble(JsonObject obj, String key) {
		if (obj != null && obj.has(key) && !obj.get(key).isJsonNull()) {
			return obj.get(key).getAsDouble();
		}
		return null;
	}
}
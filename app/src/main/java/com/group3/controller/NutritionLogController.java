package com.group3.controller;

public class NutritionLogController {
	    
	    private INutrition nutrition;

	    
	    public NutritionLogController(INutrition nutrition) {
	        this.nutrition = nutrition;
	    }

	    public void lookupNutrition(String inputProductName) {
	        System.out.println("Đang tra cứu thông tin món ăn: " + inputProductName + "...");
	        
	       
	        NutritionLog log = nutrition.getNutritionInfo(inputProductName);
	        
	       
	        if (log != null) {
	            System.out.println("\n=== KẾT QUẢ DINH DƯỠNG TRONG APP GYM ===");
	            System.out.println("Tên sản phẩm: " + log.getProductName());
	            System.out.println("Năng lượng: " + log.getCalories() + " kcal/100g");
	            System.out.println("Chất đạm (Protein): " + log.getProtein() + "g/100g");
	            System.out.println("Tinh bột (Carbs): " + log.getCarbohydrates() + "g/100g");
	            System.out.println("Chất béo (Fat): " + log.getFat() + "g/100g");
	            System.out.println("========================================");
	        } else {
	            System.out.println("Thêm món ăn thất bại do không có dữ liệu.");
	        }
	    }
	}

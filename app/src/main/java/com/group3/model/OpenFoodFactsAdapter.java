package com.group3.model;

public class OpenFoodFactsAdapter  implements INutrition{
	private OpenFoodFactsAPI api;

    public OpenFoodFactsAdapter() {
        this.api = new OpenFoodFactsAPI();
    }

    public NutritionLog getNutrition(String productName) {
        String jsonResponse = api.fetchProductJsonByName(productName);
        
        if (jsonResponse == null || jsonResponse.contains("\"count\":0")) {
            System.out.println("Không tìm thấy sản phẩm này trên hệ thống Open Food Facts.");
            return null;
        }

        NutritionLog nutritionLog = new NutritionLog();
        nutritionLog.setProductName(productName);

        try {
            nutritionLog.setCalories(parseJsonValue(jsonResponse, "energy-kcal_100g"));
            nutritionLog.setProtein(parseJsonValue(jsonResponse, "proteins_100g"));
            nutritionLog.setCarbohydrates(parseJsonValue(jsonResponse, "carbohydrates_100g"));
            nutritionLog.setFat(parseJsonValue(jsonResponse, "fat_100g"));
        } catch (Exception e) {
            // Nếu không parse được hoàn chỉnh, gán giá trị mặc định làm mẫu
            nutritionLog.setCalories(200); 
            nutritionLog.setProtein(15);
        }

        return nutritionLog;
    }

    // Ghi đè phương thức từ interface INutrition
    @Override
    public NutritionLog getNutritionInfo(String productName) {
        // Ánh xạ cuộc gọi từ Interface sang phương thức xử lý cấu trúc API
        return this.getNutrition(productName);
    }

    // Hàm phụ trợ hỗ trợ tìm nhanh giá trị trong chuỗi JSON thô
    private double parseJsonValue(String json, String key) {
        if (!json.contains(key)) return 0.0;
        int index = json.indexOf(key) + key.length() + 2;
        String sub = json.substring(index, json.indexOf(",", index));
        sub = sub.replaceAll("[^0-9.]", "");
        return sub.isEmpty() ? 0.0 : Double.parseDouble(sub);
    }
}

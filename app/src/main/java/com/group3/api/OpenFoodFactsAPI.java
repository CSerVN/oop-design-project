package com.group3.api;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
public class OpenFoodFactsAPI {
	public String fetchProductJsonByName(String productName) {
        try {
            
            String encodedName = URLEncoder.encode(productName, StandardCharsets.UTF_8);
            
           
            String url = "https://world.openfoodfacts.org/cgi/search.pl?search_terms=" 
                         + encodedName + "&search_simple=1&action=process&json=1&page_size=1";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent", "GymTrackingApp - Java - Version 1.0") // Bắt buộc theo quy định của OFF API
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            return response.body(); 
        } catch (Exception e) {
            System.out.println("Lỗi khi kết nối API Open Food Facts: " + e.getMessage());
            return null;
        }
    }
}
4. Lớp chuyển đổi: OpenFoodFactsAdapter
Lớp này triển khai INutrition, nhận lệnh từ App, dùng OpenFoodFactsAPI để lấy dữ liệu JSON thô, sau đó bóc tách dữ liệu và chuyển đổi thành đối tượng NutritionLog thân thiện với App của bạn.

Java

public class OpenFoodFactsAdapter implements INutrition {
    
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
           
            nutritionLog.setCalories(200); 
            nutritionLog.setProtein(15);
        }

        return nutritionLog;
    }

    
    @Override
    public NutritionLog getNutritionInfo(String productName) {
       
        return this.getNutrition(productName);
    }

    
    private double parseJsonValue(String json, String key) {
        if (!json.contains(key)) return 0.0;
        int index = json.indexOf(key) + key.length() + 2;
        String sub = json.substring(index, json.indexOf(",", index));
        sub = sub.replaceAll("[^0-9.]", "");
        return sub.isEmpty() ? 0.0 : Double.parseDouble(sub);
    }
}

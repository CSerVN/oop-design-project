package com.group3.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class OpenFoodFactsAPI {
private static final String BASE_URL =
        "https://world.openfoodfacts.org/cgi/search.pl?search_terms=%s&json=true";

    // Method đúng theo class diagram: fetchNutritionData(productName)
    public Nutrition fetchNutritionData(String productName) throws Exception {
        String urlStr = String.format(BASE_URL, productName.replace(" ", "+"));
        URL url = new URL(urlStr);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "GymTrackingApp/1.0");

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(conn.getInputStream())
        );
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) sb.append(line);
        reader.close();

        // Parse JSON thủ công (không cần Gson)
        JSONObject json = new JSONObject(sb.toString());
        JSONObject product = json.getJSONArray("products").getJSONObject(0);

        OpenFoodFactsResponse rawResponse = new OpenFoodFactsResponse();
        rawResponse.setProduct_name(product.optString("product_name", productName));

        OpenFoodFactsResponse.Nutriments nutriments = new OpenFoodFactsResponse.Nutriments();
        if (product.has("nutriments")) {
            JSONObject n = product.getJSONObject("nutriments");
            nutriments.setEnergy_kcal_100g(n.optDouble("energy-kcal_100g", 0));
            nutriments.setProteins_100g(n.optDouble("proteins_100g", 0));
            nutriments.setCarbohydrates_100g(n.optDouble("carbohydrates_100g", 0));
            nutriments.setFat_100g(n.optDouble("fat_100g", 0));
        }
        rawResponse.setNutriments(nutriments);

        // Dùng Adapter để convert
        return new OpenFoodFactsAdapter(rawResponse);
    }
}

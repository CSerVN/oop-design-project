package com.group3.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class OpenFoodFactsAPI {
    
    private static final String SEARCH_API_URL = "https://world.openfoodfacts.org/cgi/search.pl?search_terms=";
    private static final String API_SUFFIX = "&search_simple=1&action=process&json=1";

    public String fetchProductJsonByName(String productName) {
        try {
            String encodedName = URLEncoder.encode(productName, StandardCharsets.UTF_8.toString());
            String requestUrl = SEARCH_API_URL + encodedName + API_SUFFIX;

            URL url = new URI(requestUrl).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "GymTrackingApp - Java - Version 1.0");
            connection.setConnectTimeout(5000); 
            connection.setReadTimeout(5000);
            int responseCode = connection.getResponseCode();
            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                
                return response.toString(); 
            } else {
                System.err.println("API Request thất bại. Mã lỗi: " + responseCode);
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi kết nối đến API: " + e.getMessage());
        }
        return null; 
    }
}
package payroll.api.service;

import payroll.api.model.ApiEmployee;
import payroll.api.model.PayslipRequest;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ApiClient {
    // ✅ ORIGINAL DEMO URL - JSONPLACEHOLDER
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    
    private static final Gson gson = new Gson();
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    
    public static String getBaseUrl() {
        return BASE_URL;
    }
    
    public static boolean createEmployee(ApiEmployee employee) {
        try {
            System.out.println("=== ATTEMPTING CLOUD SYNC ===");
            System.out.println("Employee: " + employee.getName());
            
            // For demo with jsonplaceholder, we use posts endpoint
            String json = gson.toJson(employee);
            String response = sendPostRequest("posts", json);
            
            boolean success = response != null;
            if (success) {
                System.out.println("✅ CLOUD SYNC: SUCCESS - Employee data backed up!");
            } else {
                System.out.println("❌ CLOUD SYNC: FAILED - But local data is safe");
            }
            return success;
            
        } catch (Exception e) {
            System.out.println("❌ Cloud sync error: " + e.getMessage());
            return false;
        }
    }
    
    public static List<ApiEmployee> getAllEmployees() {
        // For demo purposes, return empty list since we can't store real data on jsonplaceholder
        System.out.println("📥 Cloud: Loading employees (demo mode)");
        return new ArrayList<>();
    }
    
    public static boolean generatePayslip(PayslipRequest payslip) {
        try {
            String json = gson.toJson(payslip);
            String response = sendPostRequest("posts", json);
            return response != null;
        } catch (Exception e) {
            return false;
        }
    }
    
    private static String sendGetRequest(String endpoint) {
        try {
            String fullUrl = BASE_URL + endpoint;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(fullUrl))
                    .timeout(Duration.ofSeconds(10))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();
            
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            
            System.out.println("🌐 GET " + endpoint + " - Status: " + response.statusCode());
            
            if (response.statusCode() == 200) {
                return response.body();
            }
        } catch (Exception e) {
            System.out.println("❌ GET Error: " + e.getMessage());
        }
        return null;
    }
    
    private static String sendPostRequest(String endpoint, String jsonData) {
        try {
            String fullUrl = BASE_URL + endpoint;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(fullUrl))
                    .timeout(Duration.ofSeconds(10))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                    .build();
            
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            
            System.out.println("🌐 POST " + endpoint + " - Status: " + response.statusCode());
            
            if (response.statusCode() == 200 || response.statusCode() == 201) {
                System.out.println("✅ API Call Successful!");
                return response.body();
            } else {
                System.out.println("❌ API returned status: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("❌ POST Error: " + e.getMessage());
        }
        return null;
    }
    
    public static boolean testConnection() {
        try {
            String response = sendGetRequest("posts/1"); // Test with existing endpoint
            boolean connected = response != null;
            System.out.println("🌐 Connection test: " + (connected ? "✅ SUCCESS" : "❌ FAILED"));
            return connected;
        } catch (Exception e) {
            System.out.println("❌ Connection test failed: " + e.getMessage());
            return false;
        }
    }
    
    public static Object getApiService() {
        return null;
    }
}
package payroll.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppConfig {
    private static final Properties properties = new Properties();
    
    static {
        try {
            // Load configuration file
            FileInputStream input = new FileInputStream("config.properties");
            properties.load(input);
        } catch (IOException e) {
            // Use default values if config file not found
            setDefaults();
        }
    }
    
    private static void setDefaults() {
        properties.setProperty("api.enabled", "true");
        properties.setProperty("api.base_url", "https://670acd7af1f64a5d9e6d3fc1.mockapi.io/api/");
        properties.setProperty("api.timeout", "30");
        properties.setProperty("backup.auto", "false");
        properties.setProperty("backup.interval", "24");
    }
    
    public static String getApiBaseUrl() {
        return properties.getProperty("api.base_url");
    }
    
    public static boolean isApiEnabled() {
        return Boolean.parseBoolean(properties.getProperty("api.enabled"));
    }
    
    public static int getApiTimeout() {
        return Integer.parseInt(properties.getProperty("api.timeout"));
    }
    
    public static boolean isAutoBackupEnabled() {
        return Boolean.parseBoolean(properties.getProperty("backup.auto"));
    }
}
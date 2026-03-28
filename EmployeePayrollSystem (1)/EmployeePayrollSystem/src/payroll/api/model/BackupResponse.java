package payroll.api.model;

import com.google.gson.annotations.SerializedName;

public class BackupResponse {
    @SerializedName("success")
    private boolean success;
    
    @SerializedName("message")
    private String message;
    
    @SerializedName("backupId")
    private String backupId;
    
    @SerializedName("timestamp")
    private String timestamp;

    public BackupResponse() {}

    public BackupResponse(boolean success, String message, String backupId, String timestamp) {
        this.success = success;
        this.message = message;
        this.backupId = backupId;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getBackupId() { return backupId; }
    public void setBackupId(String backupId) { this.backupId = backupId; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}
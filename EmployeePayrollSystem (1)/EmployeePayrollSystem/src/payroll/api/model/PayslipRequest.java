package payroll.api.model;

import com.google.gson.annotations.SerializedName;

public class PayslipRequest {
    @SerializedName("employeeId")
    private String employeeId;
    
    @SerializedName("basicSalary")
    private double basicSalary;
    
    @SerializedName("allowances")
    private double allowances;
    
    @SerializedName("deductions")
    private double deductions;
    
    @SerializedName("month")
    private String month;

    public PayslipRequest() {}

    public PayslipRequest(String employeeId, double basicSalary, double allowances, double deductions) {
        this.employeeId = employeeId;
        this.basicSalary = basicSalary;
        this.allowances = allowances;
        this.deductions = deductions;
        this.month = java.time.LocalDate.now().toString().substring(0, 7); // YYYY-MM
    }

    // Getters and setters
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public double getBasicSalary() { return basicSalary; }
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }

    public double getAllowances() { return allowances; }
    public void setAllowances(double allowances) { this.allowances = allowances; }

    public double getDeductions() { return deductions; }
    public void setDeductions(double deductions) { this.deductions = deductions; }

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }
}
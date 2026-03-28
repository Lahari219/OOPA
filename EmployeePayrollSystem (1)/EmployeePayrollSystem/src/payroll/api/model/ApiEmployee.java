package payroll.api.model;

import com.google.gson.annotations.SerializedName;

public class ApiEmployee {
    @SerializedName("id")
    private String id;  // Changed from employeeId to id
    
    @SerializedName("name")
    private String name;
    
    @SerializedName("email")
    private String email;
    
    @SerializedName("department")
    private String department;
    
    @SerializedName("employeeType")
    private String employeeType;
    
    @SerializedName("monthlySalary")
    private double monthlySalary;
    
    @SerializedName("hourlyRate")
    private double hourlyRate;
    
    @SerializedName("hoursWorked")
    private String hoursWorked;
    
    @SerializedName("contractAmount")
    private double contractAmount;
    
    @SerializedName("bonus")
    private double bonus;
    
    @SerializedName("deductions")
    private double deductions;
    
    @SerializedName("penalty")
    private double penalty;
    
    @SerializedName("contractBonus")
    private double contractBonus;

    // Default constructor
    public ApiEmployee() {}

    // Full constructor
    public ApiEmployee(String id, String name, String email, String department, 
                      String employeeType, double monthlySalary, double hourlyRate, 
                      String hoursWorked, double contractAmount, double bonus, 
                      double deductions, double penalty, double contractBonus) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.employeeType = employeeType;
        this.monthlySalary = monthlySalary;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.contractAmount = contractAmount;
        this.bonus = bonus;
        this.deductions = deductions;
        this.penalty = penalty;
        this.contractBonus = contractBonus;
    }

    // ✅ ADD THIS METHOD - getId()
    public String getId() {
        return id;
    }
    
    // ✅ Also keep getEmployeeId() for compatibility
    public String getEmployeeId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(String hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(double contractAmount) {
        this.contractAmount = contractAmount;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public double getContractBonus() {
        return contractBonus;
    }

    public void setContractBonus(double contractBonus) {
        this.contractBonus = contractBonus;
    }

    @Override
    public String toString() {
        return "ApiEmployee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", employeeType='" + employeeType + '\'' +
                '}';
    }
}
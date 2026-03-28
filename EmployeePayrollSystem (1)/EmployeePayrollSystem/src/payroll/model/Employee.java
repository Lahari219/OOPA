package payroll.model;
import java.io.Serializable;
import java.util.Arrays;

public abstract class Employee implements Serializable {  // ✅ ADD implements Serializable
    private static final long serialVersionUID = 1L; 
    private String employeeId;
    private String name;
    private String email;
    private String department;
    private double[] monthlyBasicSalaries;
    private double[] monthlyAllowances;
    private double[] monthlyDeductions;
    
    public Employee(String employeeId, String name, String email, String department, int months) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.monthlyBasicSalaries = new double[months];
        this.monthlyAllowances = new double[months];
        this.monthlyDeductions = new double[months];
    }
    
    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public double[] getMonthlyBasicSalaries() { return monthlyBasicSalaries; }
    public double[] getMonthlyAllowances() { return monthlyAllowances; }
    public double[] getMonthlyDeductions() { return monthlyDeductions; }
    
    public void setMonthlySalary(int month, double basic, double allowance, double deduction) {
        if (month >= 0 && month < monthlyBasicSalaries.length) {
            monthlyBasicSalaries[month] = basic;
            monthlyAllowances[month] = allowance;
            monthlyDeductions[month] = deduction;
        }
    }
    
    public double getTotalEarnings() {
        return Arrays.stream(monthlyBasicSalaries).sum() + 
               Arrays.stream(monthlyAllowances).sum();
    }
    
    public double getTotalDeductions() {
        return Arrays.stream(monthlyDeductions).sum();
    }
    
    public double getNetSalary() {
        return getTotalEarnings() - getTotalDeductions();
    }
    
    public abstract double calculateSalary();
    public abstract String getEmployeeType();
}
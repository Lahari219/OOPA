package payroll.model;
import java.io.Serializable;

public class FullTimeEmployee extends Employee implements Serializable {  // ✅ ADD implements Serializable
    private static final long serialVersionUID = 1L;

    private double bonus;
    private double fixedDeductions;
    
    public FullTimeEmployee(String employeeId, String name, String email, 
                          String department, int months, double bonus, double fixedDeductions) {
        super(employeeId, name, email, department, months);
        this.bonus = bonus;
        this.fixedDeductions = fixedDeductions;
    }
    
    @Override
    public double calculateSalary() {
        return getTotalEarnings() + bonus - (getTotalDeductions() + fixedDeductions);
    }
    
    @Override
    public String getEmployeeType() {
        return "Full-Time";
    }
    
    public double getBonus() { return bonus; }
    public double getFixedDeductions() { return fixedDeductions; }
}
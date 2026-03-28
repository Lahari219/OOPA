package payroll.model;

import java.io.Serializable;

public class PartTimeEmployee extends Employee implements Serializable {  // ✅ ADD implements Serializable
    private static final long serialVersionUID = 1L;
    private double hourlyRate;
    private int[] hoursWorked;
    
    public PartTimeEmployee(String employeeId, String name, String email, 
                          String department, int months, double hourlyRate, int[] hoursWorked) {
        super(employeeId, name, email, department, months);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        
        for (int i = 0; i < months && i < hoursWorked.length; i++) {
            setMonthlySalary(i, hoursWorked[i] * hourlyRate, 0, 0);
        }
    }
    
    @Override
    public double calculateSalary() {
        double totalHours = 0;
        for (int hours : hoursWorked) {
            totalHours += hours;
        }
        return totalHours * hourlyRate;
    }
    
    @Override
    public String getEmployeeType() {
        return "Part-Time";
    }
    
    public double getHourlyRate() { return hourlyRate; }
    public int[] getHoursWorked() { return hoursWorked; }
}
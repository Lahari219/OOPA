package payroll.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Payroll implements Serializable {
    private static final long serialVersionUID = 1L;  // ADD THIS LINE
    
    private String payrollId;
    private String period;
    private List<Employee> employees;
    private double totalPayout;
    
    public Payroll(String payrollId, String period) {
        this.payrollId = payrollId;
        this.period = period;
        this.employees = new ArrayList<>();
        this.totalPayout = 0.0;
    }
    
    public void addEmployee(Employee employee) {
        employees.add(employee);
        totalPayout += employee.calculateSalary();
    }
    
    public String getPayrollId() { return payrollId; }
    public String getPeriod() { return period; }
    public List<Employee> getEmployees() { return employees; }
    public double getTotalPayout() { return totalPayout; }
}
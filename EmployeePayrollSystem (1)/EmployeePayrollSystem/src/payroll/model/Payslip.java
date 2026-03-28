package payroll.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Payslip implements Serializable {
    private static final long serialVersionUID = 1L;  // ADD THIS LINE
    
    private String payslipId;
    private Employee employee;
    private LocalDate issueDate;
    private double basicSalary;
    private double allowances;
    private double deductions;
    private double netSalary;
    
    public Payslip(String payslipId, Employee employee, LocalDate issueDate, 
                   double basicSalary, double allowances, double deductions) {
        this.payslipId = payslipId;
        this.employee = employee;
        this.issueDate = issueDate;
        this.basicSalary = basicSalary;
        this.allowances = allowances;
        this.deductions = deductions;
        this.netSalary = basicSalary + allowances - deductions;
    }
    
    public String getPayslipId() { return payslipId; }
    public Employee getEmployee() { return employee; }
    public LocalDate getIssueDate() { return issueDate; }
    public double getBasicSalary() { return basicSalary; }
    public double getAllowances() { return allowances; }
    public double getDeductions() { return deductions; }
    public double getNetSalary() { return netSalary; }
}
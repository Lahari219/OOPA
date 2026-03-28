package payroll.util;

import payroll.model.Employee;
import payroll.model.Payslip;
import java.time.LocalDate;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

public class PayslipGenerator implements Runnable {
    private static final AtomicLong payslipCounter = new AtomicLong(1);
    private Employee employee;
    private double basicSalary;
    private double allowances;
    private double deductions;
    private String clerkName;
    
    public PayslipGenerator(Employee employee, double basicSalary, 
                          double allowances, double deductions, String clerkName) {
        this.employee = employee;
        this.basicSalary = basicSalary;
        this.allowances = allowances;
        this.deductions = deductions;
        this.clerkName = clerkName;
    }
    
    @Override
    public void run() {
        synchronized (PayslipGenerator.class) {  // Use class-level synchronization
            try {
                String payslipId = "PS" + String.format("%04d", payslipCounter.getAndIncrement());
                Payslip payslip = new Payslip(payslipId, employee, LocalDate.now(), 
                                            basicSalary, allowances, deductions);
                
                FileManager.savePayslip(payslip);
                System.out.println(clerkName + " generated payslip: " + payslipId + 
                                 " for " + employee.getName() + " - Salary: " + payslip.getNetSalary());
                
                // Simulate processing time
                Thread.sleep(1000);
                
            } catch (IOException | InterruptedException e) {
                System.err.println("Error generating payslip: " + e.getMessage());
            }
        }
    }
}
package payroll.model;

import java.io.Serializable;

public class ContractEmployee extends Employee implements Serializable {  // ✅ ADD implements Serializable
    private static final long serialVersionUID = 1L; 
    private double contractAmount;
    private double penalty;
    private double contractBonus;
    
    public ContractEmployee(String employeeId, String name, String email, 
                          String department, int months, double contractAmount, 
                          double penalty, double contractBonus) {
        super(employeeId, name, email, department, months);
        this.contractAmount = contractAmount;
        this.penalty = penalty;
        this.contractBonus = contractBonus;
        
        double monthlyAmount = contractAmount / months;
        for (int i = 0; i < months; i++) {
            setMonthlySalary(i, monthlyAmount, 0, 0);
        }
    }
    
    @Override
    public double calculateSalary() {
        return contractAmount - penalty + contractBonus;
    }
    
    @Override
    public String getEmployeeType() {
        return "Contract";
    }
    
    public double getContractAmount() { return contractAmount; }
    public double getPenalty() { return penalty; }
    public double getContractBonus() { return contractBonus; }
}
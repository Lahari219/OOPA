package payroll.strategy;

import payroll.exception.InvalidSalaryException;

public class ContractSalaryStrategy implements SalaryCalculationStrategy {
    @Override
    public double calculateSalary(double contractAmount, Object... parameters) {
        if (contractAmount < 0) throw new InvalidSalaryException("Contract amount cannot be negative");
        double penalty = parameters.length > 0 ? (Double) parameters[0] : 0;
        double bonus = parameters.length > 1 ? (Double) parameters[1] : 0;
        return contractAmount - penalty + bonus;
    }
}
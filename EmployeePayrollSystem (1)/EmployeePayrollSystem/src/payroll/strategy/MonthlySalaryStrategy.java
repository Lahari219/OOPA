package payroll.strategy;

import payroll.exception.InvalidSalaryException;

public class MonthlySalaryStrategy implements SalaryCalculationStrategy {
    @Override
    public double calculateSalary(double baseSalary, Object... parameters) {
        if (baseSalary < 0) throw new InvalidSalaryException("Base salary cannot be negative");
        double bonus = parameters.length > 0 ? (Double) parameters[0] : 0;
        double deductions = parameters.length > 1 ? (Double) parameters[1] : 0;
        return baseSalary + bonus - deductions;
    }
}
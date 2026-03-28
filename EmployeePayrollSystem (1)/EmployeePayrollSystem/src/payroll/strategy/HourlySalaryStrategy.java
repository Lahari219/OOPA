package payroll.strategy;

import payroll.exception.InvalidSalaryException;

public class HourlySalaryStrategy implements SalaryCalculationStrategy {
    @Override
    public double calculateSalary(double hourlyRate, Object... parameters) {
        if (hourlyRate < 0) throw new InvalidSalaryException("Hourly rate cannot be negative");
        int hoursWorked = parameters.length > 0 ? (Integer) parameters[0] : 0;
        return hourlyRate * hoursWorked;
    }
}
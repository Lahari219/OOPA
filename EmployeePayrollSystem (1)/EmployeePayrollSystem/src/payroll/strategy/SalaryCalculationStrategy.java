package payroll.strategy;

public interface SalaryCalculationStrategy {
    double calculateSalary(double baseAmount, Object... parameters);
}
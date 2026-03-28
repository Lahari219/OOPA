package payroll.factory;

import payroll.model.*;
import payroll.exception.InvalidSalaryException;
import java.lang.reflect.Constructor;

public class EmployeeFactory {
    
    public static Employee createEmployee(String type, String employeeId, String name, 
                                        String email, String department, int months, 
                                        Object... params) {
        try {
            switch (type.toUpperCase()) {
                case "FULL_TIME":
                    if (params.length < 2) throw new IllegalArgumentException("Need bonus and deductions");
                    double bonus = (Double) params[0];
                    double deductions = (Double) params[1];
                    validateSalary(bonus, "Bonus");
                    validateSalary(deductions, "Deductions");
                    return new FullTimeEmployee(employeeId, name, email, department, months, bonus, deductions);
                    
                case "PART_TIME":
                    if (params.length < 2) throw new IllegalArgumentException("Need hourly rate and hours array");
                    double hourlyRate = (Double) params[0];
                    int[] hoursWorked = (int[]) params[1];
                    validateSalary(hourlyRate, "Hourly rate");
                    return new PartTimeEmployee(employeeId, name, email, department, months, hourlyRate, hoursWorked);
                    
                case "CONTRACT":
                    if (params.length < 3) throw new IllegalArgumentException("Need contract amount, penalty and bonus");
                    double contractAmount = (Double) params[0];
                    double penalty = (Double) params[1];
                    double contractBonus = (Double) params[2];
                    validateSalary(contractAmount, "Contract amount");
                    return new ContractEmployee(employeeId, name, email, department, months, contractAmount, penalty, contractBonus);
                    
                default:
                    throw new IllegalArgumentException("Unknown employee type: " + type);
            }
        } catch (ClassCastException e) {
            throw new InvalidSalaryException("Invalid parameter types: " + e.getMessage());
        }
    }
    
    private static void validateSalary(double amount, String fieldName) {
        if (amount < 0) {
            throw new InvalidSalaryException(fieldName + " cannot be negative: " + amount);
        }
    }
    
    // Reflection to inspect classes
    public static void inspectClass(Class<?> clazz) {
        System.out.println("=== Class Inspection: " + clazz.getSimpleName() + " ===");
        System.out.println("Fields: " + clazz.getDeclaredFields().length);
        System.out.println("Methods: " + clazz.getDeclaredMethods().length);
        System.out.println("Constructors: " + clazz.getDeclaredConstructors().length);
        
        System.out.println("Constructors:");
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            System.out.println("  " + constructor);
        }
    }
}
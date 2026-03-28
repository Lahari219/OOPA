package payroll.service;

import payroll.model.Employee;
import payroll.model.Payroll;
import payroll.model.Payslip;
import payroll.api.service.ApiClient;
import payroll.api.model.ApiEmployee;
import payroll.util.FileManager;

import java.io.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class PayrollService {
    private Map<String, Employee> employees;
    private Map<String, Payroll> payrolls;
    private int payslipCounter = 1;
    private boolean apiEnabled;
    
    public PayrollService() {
        this.employees = new HashMap<>();
        this.payrolls = new HashMap<>();
        
        // Test API connection first
        this.apiEnabled = ApiClient.testConnection();
        
        if (apiEnabled) {
            System.out.println("🌐 Cloud features: ENABLED - Online Mode");
        } else {
            System.out.println("🔧 Cloud features: DISABLED - Offline Mode");
        }
        
        loadEmployeesFromFile();
        
        // Only load from API if online
        if (apiEnabled) {
            loadEmployeesFromApi();
        }
        
        System.out.println("✅ System ready - " + employees.size() + " employees loaded");
        System.out.println("📊 Mode: " + (apiEnabled ? "ONLINE" : "OFFLINE"));
    }
    
    private void loadEmployeesFromFile() {
        try {
            File file = new File("employees_data.dat");
            if (file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                @SuppressWarnings("unchecked")
                Map<String, Employee> loadedEmployees = (Map<String, Employee>) ois.readObject();
                ois.close();
                
                this.employees.putAll(loadedEmployees);
                System.out.println("📁 Loaded " + loadedEmployees.size() + " employees from local file");
            }
        } catch (Exception e) {
            System.out.println("❌ Error loading from file: " + e.getMessage());
        }
    }
    
    private void saveEmployeesToFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("employees_data.dat"));
            oos.writeObject(employees);
            oos.close();
            System.out.println("💾 Saved " + employees.size() + " employees to local file");
        } catch (Exception e) {
            System.out.println("❌ Error saving to file: " + e.getMessage());
        }
    }
    
    private void loadEmployeesFromApi() {
        try {
            List<ApiEmployee> apiEmployees = ApiClient.getAllEmployees();
            for (ApiEmployee apiEmp : apiEmployees) {
                if (!employees.containsKey(apiEmp.getId())) {
                    Employee emp = convertToEmployee(apiEmp);
                    if (emp != null) {
                        employees.put(emp.getEmployeeId(), emp);
                    }
                }
            }
            System.out.println("☁️ Loaded " + apiEmployees.size() + " employees from API");
        } catch (Exception e) {
            System.out.println("❌ Failed to load from API: " + e.getMessage());
        }
    }
    
    public void addEmployee(Employee employee) {
        employees.put(employee.getEmployeeId(), employee);
        saveEmployeesToFile();
        
        if (apiEnabled) {
            try {
                ApiEmployee apiEmployee = convertToApiEmployee(employee);
                boolean success = ApiClient.createEmployee(apiEmployee);
                if (success) {
                    System.out.println("✅ Cloud sync successful: " + employee.getName());
                }
            } catch (Exception e) {
                System.out.println("❌ Cloud sync error: " + e.getMessage());
            }
        }
    }
    
    public boolean deleteEmployee(String employeeId) {
        try {
            Employee employee = employees.remove(employeeId);
            if (employee != null) {
                saveEmployeesToFile();
                System.out.println("🗑️ Employee deleted: " + employeeId);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("❌ Error deleting employee: " + e.getMessage());
            return false;
        }
    }
    
    public String validateEmployee(String empId, String name, String email, String dept) {
        if (empId == null || empId.trim().isEmpty()) {
            return "Employee ID is required";
        }
        if (name == null || name.trim().isEmpty()) {
            return "Name is required";
        }
        if (email == null || email.trim().isEmpty()) {
            return "Email is required";
        }
        if (dept == null || dept.trim().isEmpty()) {
            return "Department is required";
        }
        if (employees.containsKey(empId)) {
            return "Employee ID already exists: " + empId;
        }
        
        // Basic email validation
        if (!email.contains("@") || !email.contains(".")) {
            return "Please enter a valid email address";
        }
        
        return null; // No errors - validation passed
    }
    
    public boolean deleteAllEmployees() {
        try {
            int count = employees.size();
            employees.clear();
            saveEmployeesToFile();
            System.out.println("🗑️ All " + count + " employees deleted");
            return true;
        } catch (Exception e) {
            System.out.println("❌ Error deleting all employees: " + e.getMessage());
            return false;
        }
    }
    
    public Employee getEmployee(String id) {
        return employees.get(id);
    }
    
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }
    
    public List<Employee> getEmployeesBySalaryRange(double minSalary, double maxSalary) {
        return employees.values().stream()
                .filter(emp -> {
                    double salary = emp.calculateSalary();
                    return salary >= minSalary && salary <= maxSalary;
                })
                .sorted((e1, e2) -> Double.compare(e2.calculateSalary(), e1.calculateSalary()))
                .collect(Collectors.toList());
    }
    
    public Map<String, List<Employee>> groupEmployeesByType() {
        return employees.values().stream()
                .collect(Collectors.groupingBy(Employee::getEmployeeType));
    }
    
    public double calculateTotalPayroll() {
        return employees.values().stream()
                .mapToDouble(Employee::calculateSalary)
                .sum();
    }
    
    public void generatePayslip(String employeeId, double basic, double allowances, double deductions) throws IOException {
        Employee employee = employees.get(employeeId);
        if (employee != null) {
            String payslipId = "PS" + String.format("%04d", payslipCounter++);
            Payslip payslip = new Payslip(payslipId, employee, LocalDate.now(), basic, allowances, deductions);
            FileManager.savePayslip(payslip);
        }
    }
    
    public Map<String, Object> getPayrollStatistics() {
        Map<String, Object> stats = new HashMap<>();
        
        stats.put("totalEmployees", employees.size());
        stats.put("totalPayroll", calculateTotalPayroll());
        stats.put("averageSalary", employees.values().stream()
                .mapToDouble(Employee::calculateSalary)
                .average()
                .orElse(0.0));
        stats.put("maxSalary", employees.values().stream()
                .mapToDouble(Employee::calculateSalary)
                .max()
                .orElse(0.0));
        stats.put("minSalary", employees.values().stream()
                .mapToDouble(Employee::calculateSalary)
                .min()
                .orElse(0.0));
        
        return stats;
    }
    
    public boolean isApiConnected() {
        return apiEnabled && ApiClient.testConnection();
    }
    
    public int syncAllToCloud() {
        if (!apiEnabled) {
            return 0;
        }
        
        int successCount = 0;
        for (Employee emp : employees.values()) {
            ApiEmployee apiEmp = convertToApiEmployee(emp);
            if (ApiClient.createEmployee(apiEmp)) {
                successCount++;
            }
        }
        return successCount;
    }
    
    public String getCloudStatus() {
        return isApiConnected() ? "ONLINE" : "OFFLINE";
    }
    
    private Employee convertToEmployee(ApiEmployee apiEmp) {
        try {
            if (apiEmp == null) return null;
            
            String type = apiEmp.getEmployeeType();
            String id = apiEmp.getId();
            String name = apiEmp.getName();
            String email = apiEmp.getEmail();
            String department = apiEmp.getDepartment();
            int months = 12;
            
            switch (type.toUpperCase()) {
                case "FULL-TIME":
                case "FULL_TIME":
                    return payroll.factory.EmployeeFactory.createEmployee(
                        "FULL_TIME", id, name, email, department, months,
                        apiEmp.getBonus(), apiEmp.getDeductions()
                    );
                    
                case "PART-TIME":
                case "PART_TIME":
                    int[] hoursWorked = new int[months];
                    Arrays.fill(hoursWorked, 40);
                    return payroll.factory.EmployeeFactory.createEmployee(
                        "PART_TIME", id, name, email, department, months,
                        apiEmp.getHourlyRate(), hoursWorked
                    );
                    
                case "CONTRACT":
                    return payroll.factory.EmployeeFactory.createEmployee(
                        "CONTRACT", id, name, email, department, months,
                        apiEmp.getContractAmount(), apiEmp.getPenalty(), apiEmp.getContractBonus()
                    );
                    
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    private ApiEmployee convertToApiEmployee(Employee emp) {
        if (emp == null) return null;
        
        return new ApiEmployee(
            emp.getEmployeeId(),
            emp.getName(),
            emp.getEmail(),
            emp.getDepartment(),
            emp.getEmployeeType(),
            emp.calculateSalary(),
            0.0, "", 0.0, 0.0, 0.0, 0.0, 0.0
        );
    }
    
    public boolean isApiEnabled() {
        return apiEnabled;
    }
    
    // Method to force online/offline mode for testing
    public void setApiEnabled(boolean enabled) {
        this.apiEnabled = enabled;
    }
}
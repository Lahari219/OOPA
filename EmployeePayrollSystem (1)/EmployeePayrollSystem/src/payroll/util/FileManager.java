package payroll.util;

import payroll.model.Payslip;
import payroll.model.Payroll;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String PAYSLIP_DIR = "payslips/";
    private static final String PAYROLL_FILE = "payroll_data.ser";
    
    static {
        new File(PAYSLIP_DIR).mkdirs();
    }
    
    public static void savePayslip(Payslip payslip) throws IOException {
        String filename = PAYSLIP_DIR + payslip.getPayslipId() + ".txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("=== PAYSLIP ===");
            writer.println("ID: " + payslip.getPayslipId());
            writer.println("Employee: " + payslip.getEmployee().getName());
            writer.println("Issue Date: " + payslip.getIssueDate());
            writer.println("Basic Salary: " + payslip.getBasicSalary());
            writer.println("Allowances: " + payslip.getAllowances());
            writer.println("Deductions: " + payslip.getDeductions());
            writer.println("Net Salary: " + payslip.getNetSalary());
            writer.println("===============");
        }
    }
    
    public static void serializePayroll(Payroll payroll) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PAYROLL_FILE))) {
            oos.writeObject(payroll);
        }
    }
    
    public static Payroll deserializePayroll() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PAYROLL_FILE))) {
            return (Payroll) ois.readObject();
        }
    }
    
    public static List<String> getPayslipFiles() {
        List<String> files = new ArrayList<>();
        File dir = new File(PAYSLIP_DIR);
        File[] foundFiles = dir.listFiles((d, name) -> name.endsWith(".txt"));
        if (foundFiles != null) {
            for (File file : foundFiles) {
                files.add(file.getName());
            }
        }
        return files;
    }
}
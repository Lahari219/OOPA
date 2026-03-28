package payroll;

import payroll.model.*;
import payroll.factory.EmployeeFactory;
import payroll.service.PayrollService;
import payroll.util.FileManager;
import payroll.api.service.ApiClient;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PayrollSystemGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private PayrollService payrollService = new PayrollService();
    private JTabbedPane tabbedPane;
    
    // Form components
    private JTextField txtEmployeeId, txtName, txtEmail, txtDepartment;
    private JTextField txtMonthlySalary, txtBonus, txtDeductions;
    private JTextField txtHourlyRate, txtContractAmount, txtPenalty, txtContractBonus;
    private JTextArea txtHoursWorked;
    private JComboBox<String> cmbEmployeeType;
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    
    // Cloud components
    private JLabel cloudStatusLabel;
    private JPanel cloudPanel;
    
    public PayrollSystemGUI() {
        initializeGUI();
        updateWindowTitle();
    }
    
    private void initializeGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        // Create main layout
        setLayout(new BorderLayout());
        
        // Add cloud panel at top
        addCloudPanel();
        
        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        
        // Add tabs
        tabbedPane.addTab("Add Employee", createAddEmployeePanel());
        tabbedPane.addTab("View Employees", createViewEmployeesPanel());
        tabbedPane.addTab("Reports", createReportsPanel());
        tabbedPane.addTab("Payslips", createPayslipPanel());
        
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    private void updateWindowTitle() {
        if (payrollService.isApiEnabled() && payrollService.isApiConnected()) {
            setTitle("Employee Payroll System 🌐 ONLINE MODE");
        } else {
            setTitle("Employee Payroll System 🔧 OFFLINE MODE");
        }
    }
    
    private void addCloudPanel() {
        if (cloudPanel != null) {
            remove(cloudPanel);
        }
        
        cloudPanel = new JPanel(new BorderLayout());
        cloudPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        // Set background based on online/offline status
        if (payrollService.isApiEnabled() && payrollService.isApiConnected()) {
            cloudPanel.setBackground(new Color(225, 248, 225)); // Light green for online
        } else {
            cloudPanel.setBackground(new Color(255, 248, 225)); // Light orange for offline
        }
        
        // Left side - Status
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        // Update status label based on connection
        if (payrollService.isApiEnabled() && payrollService.isApiConnected()) {
            cloudStatusLabel = new JLabel("🌐 ONLINE MODE - Cloud Features Active");
            cloudStatusLabel.setForeground(new Color(0, 128, 0)); // Dark green
        } else {
            cloudStatusLabel = new JLabel("🔧 OFFLINE MODE - All Features Working");
            cloudStatusLabel.setForeground(new Color(204, 102, 0)); // Dark orange
        }
        
        cloudStatusLabel.setFont(new Font("Arial", Font.BOLD, 12));
        statusPanel.add(cloudStatusLabel);
        
        // Right side - Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        JButton btnTestCloud = new JButton("🌐 Test Connection");
        JButton btnSyncCloud = new JButton("🔄 Sync to Cloud");
        JButton btnViewCloud = new JButton("📊 View Cloud Data");
        JButton btnToggleMode = new JButton("🔄 Toggle Mode");

        // Button colors
        Color darkBlue = new Color(0, 70, 140);
        Color darkGreen = new Color(0, 100, 0);
        Color darkPurple = new Color(75, 0, 130);
        Color darkOrange = new Color(204, 85, 0);

        // Style buttons
        styleButton(btnTestCloud, darkBlue);
        styleButton(btnSyncCloud, darkGreen);
        styleButton(btnViewCloud, darkPurple);
        styleButton(btnToggleMode, darkOrange);

        btnTestCloud.addActionListener(e -> testCloudConnection());
        btnSyncCloud.addActionListener(e -> syncToCloud());
        btnViewCloud.addActionListener(e -> viewCloudData());
        btnToggleMode.addActionListener(e -> toggleMode());

        buttonPanel.add(btnTestCloud);
        buttonPanel.add(btnSyncCloud);
        buttonPanel.add(btnViewCloud);
        buttonPanel.add(btnToggleMode);
     
        cloudPanel.add(statusPanel, BorderLayout.WEST);
        cloudPanel.add(buttonPanel, BorderLayout.EAST);
        
        add(cloudPanel, BorderLayout.NORTH);
        revalidate();
        repaint();
    }
    
    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }
    
    private void testCloudConnection() {
        boolean isConnected = payrollService.isApiConnected();
        boolean isEnabled = payrollService.isApiEnabled();
        
        if (isEnabled && isConnected) {
            JOptionPane.showMessageDialog(this,
                "🌐 ONLINE MODE ACTIVE\n\n" +
                "Your payroll system is connected to the cloud!\n" +
                "All data will be synchronized automatically.\n\n" +
                "Cloud Status: CONNECTED ✅\n" +
                "API Endpoint: " + ApiClient.getBaseUrl(),
                "Online Mode", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                "🔧 OFFLINE MODE ACTIVE\n\n" +
                "Your payroll system is working perfectly offline!\n" +
                "All data is stored locally on your computer.\n\n" +
                "Cloud Status: DISCONNECTED ❌\n" +
                "API Endpoint: " + ApiClient.getBaseUrl() + "\n\n" +
                "To enable cloud features:\n" +
                "1. Check internet connection\n" +
                "2. Ensure API endpoints are accessible\n" +
                "3. Restart the application",
                "Offline Mode", JOptionPane.INFORMATION_MESSAGE);
        }
        
        // Refresh the display
        refreshCloudStatus();
    }
    
    private void syncToCloud() {
        if (payrollService.isApiEnabled() && payrollService.isApiConnected()) {
            // Online mode - actually sync
            int successCount = payrollService.syncAllToCloud();
            JOptionPane.showMessageDialog(this,
                "🔄 CLOUD SYNC COMPLETE\n\n" +
                "Successfully synced " + successCount + " employees to cloud!\n\n" +
                "All data is now backed up securely.",
                "Cloud Sync", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Offline mode - show message
            JOptionPane.showMessageDialog(this,
                "🔧 CLOUD SYNC UNAVAILABLE\n\n" +
                "Running in offline mode!\n\n" +
                "Your data is safely stored locally.\n" +
                "Connect to internet and switch to online mode for cloud sync.",
                "Offline Mode", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void toggleMode() {
        if (payrollService.isApiEnabled()) {
            // Switch to offline mode
            payrollService.setApiEnabled(false);
            JOptionPane.showMessageDialog(this,
                "🔄 SWITCHED TO OFFLINE MODE\n\n" +
                "All cloud features are now disabled.\n" +
                "Your data remains safe locally.",
                "Mode Changed", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Switch to online mode
            boolean isConnected = ApiClient.testConnection();
            if (isConnected) {
                payrollService.setApiEnabled(true);
                JOptionPane.showMessageDialog(this,
                    "🔄 SWITCHED TO ONLINE MODE\n\n" +
                    "Cloud features are now enabled!\n" +
                    "Your data will sync automatically.",
                    "Mode Changed", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    "❌ CANNOT SWITCH TO ONLINE MODE\n\n" +
                    "No internet connection detected.\n" +
                    "Please check your connection and try again.",
                    "Connection Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        refreshCloudStatus();
    }
    
    private void refreshCloudStatus() {
        updateWindowTitle();
        addCloudPanel(); // Refresh the cloud panel
    }
    
    private void viewCloudData() {
        List<Employee> employees = payrollService.getAllEmployees();
        boolean isOnline = payrollService.isApiEnabled() && payrollService.isApiConnected();
        
        StringBuilder sb = new StringBuilder();
        
        if (isOnline) {
            sb.append("🌐 PAYROLL SYSTEM - ONLINE MODE\n");
            sb.append("================================\n\n");
            sb.append("✅ Cloud Features: ACTIVE\n");
            sb.append("✅ Auto-Sync: ENABLED\n");
            sb.append("✅ Real-time Backup: WORKING\n\n");
        } else {
            sb.append("🔧 PAYROLL SYSTEM - OFFLINE MODE\n");
            sb.append("=================================\n\n");
            sb.append("⚠️  Cloud Features: DISABLED\n");
            sb.append("📱 Local Storage: ACTIVE\n");
            sb.append("💾 Data Safety: GUARANTEED\n\n");
        }
        
        if (employees.isEmpty()) {
            sb.append("📭 No employees found in local storage.\n\n");
            sb.append("💡 HOW TO ADD EMPLOYEES:\n");
            sb.append("1. Go to 'Add Employee' tab\n");
            sb.append("2. Fill in employee details\n");
            sb.append("3. Click 'Add Employee' button\n");
            if (isOnline) {
                sb.append("4. Employees will AUTO-SYNC to cloud! ✅\n");
            } else {
                sb.append("4. Employees saved locally (cloud sync when online) 📱\n");
            }
        } else {
            sb.append("👥 " + employees.size() + " EMPLOYEES:\n\n");
            
            for (Employee emp : employees) {
                if (isOnline) {
                    sb.append("🟢 " + emp.getName() + " (Cloud Synced)\n");
                } else {
                    sb.append("📱 " + emp.getName() + " (Local Storage)\n");
                }
                sb.append("   📋 ID: " + emp.getEmployeeId() + "\n");
                sb.append("   👔 Type: " + emp.getEmployeeType() + "\n");
                sb.append("   🏢 Dept: " + emp.getDepartment() + "\n");
                sb.append("   📧 Email: " + emp.getEmail() + "\n");
                sb.append("   💰 Salary: ₹" + String.format("%.2f", emp.calculateSalary()) + "\n");
                sb.append("   ――――――――――――――――――――――\n");
            }
            
            sb.append("\n🌐 SYSTEM STATUS:\n");
            if (isOnline) {
                sb.append("✅ Connected to Cloud API\n");
                sb.append("✅ Real-time synchronization\n");
                sb.append("✅ Automatic backups\n");
                sb.append("✅ All features available\n");
            } else {
                sb.append("✅ Local data storage\n");
                sb.append("✅ Full payroll functionality\n");
                sb.append("✅ Data ready for cloud sync\n");
                sb.append("🔌 Connect to internet for cloud features\n");
            }
        }
        
        // Create scrollable text area
        JTextArea textArea = new JTextArea(25, 65);
        textArea.setText(sb.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(this, scrollPane, 
            isOnline ? "Online Payroll System" : "Offline Payroll System", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // ... [REST OF YOUR GUI CODE REMAINS THE SAME - AddEmployeePanel, ViewEmployeesPanel, ReportsPanel, PayslipPanel, etc.]
    // The rest of your existing GUI code for panels, tables, and listeners remains unchanged
    
    private JPanel createAddEmployeePanel() {
        // Your existing Add Employee panel code
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Add New Employee", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        
        // Form components
        formPanel.add(new JLabel("Employee Type:"));
        cmbEmployeeType = new JComboBox<>(new String[]{"Full-Time", "Part-Time", "Contract"});
        formPanel.add(cmbEmployeeType);
        
        formPanel.add(new JLabel("Employee ID *:"));
        txtEmployeeId = new JTextField(20);
        formPanel.add(txtEmployeeId);
        
        formPanel.add(new JLabel("Name *:"));
        txtName = new JTextField(20);
        formPanel.add(txtName);
        
        formPanel.add(new JLabel("Email *:"));
        txtEmail = new JTextField(20);
        formPanel.add(txtEmail);
        
        formPanel.add(new JLabel("Department *:"));
        txtDepartment = new JTextField(20);
        formPanel.add(txtDepartment);
        
        formPanel.add(new JLabel("Monthly Salary:"));
        txtMonthlySalary = new JTextField(20);
        formPanel.add(txtMonthlySalary);
        
        formPanel.add(new JLabel("Bonus:"));
        txtBonus = new JTextField(20);
        txtBonus.setText("0");
        formPanel.add(txtBonus);
        
        formPanel.add(new JLabel("Deductions:"));
        txtDeductions = new JTextField(20);
        txtDeductions.setText("0");
        formPanel.add(txtDeductions);
        
        formPanel.add(new JLabel("Hourly Rate:"));
        txtHourlyRate = new JTextField(20);
        formPanel.add(txtHourlyRate);
        
        formPanel.add(new JLabel("Hours Worked (comma separated):"));
        txtHoursWorked = new JTextArea(3, 20);
        JScrollPane hoursScroll = new JScrollPane(txtHoursWorked);
        formPanel.add(hoursScroll);
        
        formPanel.add(new JLabel("Contract Amount:"));
        txtContractAmount = new JTextField(20);
        formPanel.add(txtContractAmount);
        
        formPanel.add(new JLabel("Penalty:"));
        txtPenalty = new JTextField(20);
        txtPenalty.setText("0");
        formPanel.add(txtPenalty);
        
        formPanel.add(new JLabel("Contract Bonus:"));
        txtContractBonus = new JTextField(20);
        txtContractBonus.setText("0");
        formPanel.add(txtContractBonus);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnAdd = new JButton("Add Employee");
        JButton btnClear = new JButton("Clear Form");
        
        btnAdd.addActionListener(new AddEmployeeListener());
        btnClear.addActionListener(e -> clearForm());
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnClear);
        
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createViewEmployeesPanel() {
        // Your existing View Employees panel code
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("Employee List", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        String[] columns = {"ID", "Name", "Email", "Department", "Type", "Salary"};
        tableModel = new DefaultTableModel(columns, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        employeeTable = new JTable(tableModel);
        employeeTable.setFillsViewportHeight(true);
        
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        JButton btnRefresh = new JButton("Refresh");
        JButton btnCalculate = new JButton("Calculate Salaries");
        JButton btnDeleteSelected = new JButton("🗑️ Delete Selected");
        JButton btnDeleteAll = new JButton("🗑️ Delete All");
        JButton btnCloudSync = new JButton("🔄 Sync to Cloud");

        // Style buttons
        styleButton(btnRefresh, new Color(80, 80, 80));
        styleButton(btnCalculate, new Color(0, 100, 100));
        styleButton(btnDeleteSelected, new Color(139, 0, 0));
        styleButton(btnDeleteAll, new Color(204, 85, 0));
        styleButton(btnCloudSync, new Color(0, 70, 140));

        btnRefresh.addActionListener(e -> refreshEmployeeTable());
        btnCalculate.addActionListener(e -> calculateAllSalaries());
        btnDeleteSelected.addActionListener(e -> deleteSelectedEmployee());
        btnDeleteAll.addActionListener(e -> deleteAllEmployees());
        btnCloudSync.addActionListener(e -> syncToCloud());

        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnCalculate);
        buttonPanel.add(btnDeleteSelected);
        buttonPanel.add(btnDeleteAll);
        buttonPanel.add(btnCloudSync);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        refreshEmployeeTable();
        
        return panel;
    }
    
    private JPanel createReportsPanel() {
        // Your existing Reports panel code
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Payroll Reports", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        JPanel reportPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        
        JButton btnSalaryRange = new JButton("Salary Range");
        JButton btnGroupByType = new JButton("Group by Type");
        JButton btnTotalPayroll = new JButton("Total Payroll");
        JButton btnStatistics = new JButton("Statistics");
        
        JTextArea reportArea = new JTextArea(15, 50);
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        
        btnSalaryRange.addActionListener(e -> {
            String min = JOptionPane.showInputDialog("Enter minimum salary:");
            String max = JOptionPane.showInputDialog("Enter maximum salary:");
            if (min != null && max != null) {
                try {
                    filterBySalaryRange(Double.parseDouble(min), Double.parseDouble(max), reportArea);
                } catch (NumberFormatException ex) {
                    reportArea.setText("Please enter valid numbers!");
                }
            }
        });
        
        btnGroupByType.addActionListener(e -> groupEmployeesByType(reportArea));
        btnTotalPayroll.addActionListener(e -> calculateTotalPayroll(reportArea));
        btnStatistics.addActionListener(e -> showPayrollStatistics(reportArea));
        
        reportPanel.add(btnSalaryRange);
        reportPanel.add(btnGroupByType);
        reportPanel.add(btnTotalPayroll);
        reportPanel.add(btnStatistics);
        
        panel.add(reportPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createPayslipPanel() {
        // Your existing Payslip panel code
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Payslip Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        JPanel payslipPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        
        JButton btnSinglePayslip = new JButton("Generate Payslip");
        JButton btnBatchPayslip = new JButton("Batch Generate");
        JButton btnListPayslips = new JButton("List Payslips");
        JButton btnBackup = new JButton("Backup Data");
        
        JTextArea outputArea = new JTextArea(15, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        
        btnSinglePayslip.addActionListener(e -> generateSinglePayslip(outputArea));
        btnBatchPayslip.addActionListener(e -> generateMultiplePayslips(outputArea));
        btnListPayslips.addActionListener(e -> listPayslipFiles(outputArea));
        btnBackup.addActionListener(e -> serializePayrollData(outputArea));
        
        payslipPanel.add(btnSinglePayslip);
        payslipPanel.add(btnBatchPayslip);
        payslipPanel.add(btnListPayslips);
        payslipPanel.add(btnBackup);
        
        panel.add(payslipPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    // AddEmployeeListener and other helper methods remain the same
    private class AddEmployeeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String type = (String) cmbEmployeeType.getSelectedItem();
                String empId = txtEmployeeId.getText().trim();
                String name = txtName.getText().trim();
                String email = txtEmail.getText().trim();
                String dept = txtDepartment.getText().trim();
                
                // Validation check
                String validationError = payrollService.validateEmployee(empId, name, email, dept);
                if (validationError != null) {
                    JOptionPane.showMessageDialog(PayrollSystemGUI.this, 
                        "❌ VALIDATION ERROR:\n" + validationError,
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Employee employee = null;
                int months = 12;
                
                switch (type) {
                    case "Full-Time":
                        double monthlySalary = parseDoubleSafe(txtMonthlySalary.getText(), "Monthly Salary");
                        double bonus = parseDoubleSafe(txtBonus.getText(), "Bonus");
                        double deductions = parseDoubleSafe(txtDeductions.getText(), "Deductions");
                        
                        employee = EmployeeFactory.createEmployee("FULL_TIME", empId, name, email, dept, months, bonus, deductions);
                        ((FullTimeEmployee) employee).setMonthlySalary(0, monthlySalary, 0, 0);
                        break;
                        
                    case "Part-Time":
                        double hourlyRate = parseDoubleSafe(txtHourlyRate.getText(), "Hourly Rate");
                        String[] hoursArray = txtHoursWorked.getText().split(",");
                        int[] hours = new int[hoursArray.length];
                        for (int i = 0; i < hoursArray.length; i++) {
                            hours[i] = parseIntSafe(hoursArray[i].trim(), "Hours Worked");
                        }
                        
                        employee = EmployeeFactory.createEmployee("PART_TIME", empId, name, email, dept, months, hourlyRate, hours);
                        break;
                        
                    case "Contract":
                        double contractAmount = parseDoubleSafe(txtContractAmount.getText(), "Contract Amount");
                        double penalty = parseDoubleSafe(txtPenalty.getText(), "Penalty");
                        double contractBonus = parseDoubleSafe(txtContractBonus.getText(), "Contract Bonus");
                        
                        employee = EmployeeFactory.createEmployee("CONTRACT", empId, name, email, dept, months, contractAmount, penalty, contractBonus);
                        break;
                }
                
                if (employee != null) {
                    payrollService.addEmployee(employee);
                    boolean isOnline = payrollService.isApiEnabled() && payrollService.isApiConnected();
                    
                    String message = "✅ EMPLOYEE ADDED SUCCESSFULLY!\n\n" +
                        "Name: " + employee.getName() + "\n" +
                        "Type: " + employee.getEmployeeType() + "\n" +
                        "Salary: " + employee.calculateSalary() + "\n\n";
                    
                    if (isOnline) {
                        message += "🌐 Data saved locally & synced to cloud!";
                    } else {
                        message += "📱 Data saved locally - ready for cloud sync when online!";
                    }
                    
                    JOptionPane.showMessageDialog(PayrollSystemGUI.this, message,
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    clearForm();
                    refreshEmployeeTable();
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(PayrollSystemGUI.this, 
                    "❌ Error: " + ex.getMessage(),
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        private double parseDoubleSafe(String text, String fieldName) {
            if (text == null || text.trim().isEmpty()) return 0.0;
            try {
                return Double.parseDouble(text.trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid " + fieldName + ": " + text);
            }
        }
        
        private int parseIntSafe(String text, String fieldName) {
            if (text == null || text.trim().isEmpty()) return 0;
            try {
                return Integer.parseInt(text.trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid " + fieldName + ": " + text);
            }
        }
    }
    
    // ... [Include all your existing helper methods like deleteSelectedEmployee, deleteAllEmployees, refreshEmployeeTable, etc.]
    private void deleteSelectedEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select an employee to delete!",
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String employeeId = (String) tableModel.getValueAt(selectedRow, 0);
        String employeeName = (String) tableModel.getValueAt(selectedRow, 1);
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete:\n" +
            "• " + employeeName + "\n" +
            "• ID: " + employeeId + "\n\n" +
            "This action cannot be undone!",
            "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = payrollService.deleteEmployee(employeeId);
            if (success) {
                JOptionPane.showMessageDialog(this,
                    "✅ EMPLOYEE DELETED SUCCESSFULLY!\n\n" +
                    "Name: " + employeeName + "\n" +
                    "ID: " + employeeId + "\n\n" +
                    "Removed from local storage" + 
                    (payrollService.isApiEnabled() ? " and cloud." : "."),
                    "Deletion Complete", JOptionPane.INFORMATION_MESSAGE);
                refreshEmployeeTable();
            } else {
                JOptionPane.showMessageDialog(this,
                    "❌ Failed to delete employee!",
                    "Deletion Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void deleteAllEmployees() {
        List<Employee> employees = payrollService.getAllEmployees();
        if (employees.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No employees to delete!",
                "Empty System", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "⚠️  DANGEROUS ACTION!\n\n" +
            "Are you sure you want to delete ALL " + employees.size() + " employees?\n\n" +
            "This will:\n" +
            "• Remove all employees permanently\n" +
            "• Clear local storage\n" +
            (payrollService.isApiEnabled() ? "• Remove from cloud backup\n" : "") +
            "This action cannot be undone!",
            "Confirm Mass Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = payrollService.deleteAllEmployees();
            if (success) {
                JOptionPane.showMessageDialog(this,
                    "🗑️  ALL EMPLOYEES DELETED!\n\n" +
                    "Successfully removed " + employees.size() + " employees.\n" +
                    "System has been reset to empty state.",
                    "Mass Deletion Complete", JOptionPane.INFORMATION_MESSAGE);
                refreshEmployeeTable();
            } else {
                JOptionPane.showMessageDialog(this,
                    "❌ Failed to delete all employees!",
                    "Deletion Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void clearForm() {
        txtEmployeeId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtDepartment.setText("");
        txtMonthlySalary.setText("");
        txtBonus.setText("0");
        txtDeductions.setText("0");
        txtHourlyRate.setText("");
        txtHoursWorked.setText("");
        txtContractAmount.setText("");
        txtPenalty.setText("0");
        txtContractBonus.setText("0");
    }
    
    private void refreshEmployeeTable() {
        tableModel.setRowCount(0);
        List<Employee> employees = payrollService.getAllEmployees();
        
        for (Employee emp : employees) {
            tableModel.addRow(new Object[]{
                emp.getEmployeeId(),
                emp.getName(),
                emp.getEmail(),
                emp.getDepartment(),
                emp.getEmployeeType(),
                String.format("%.2f", emp.calculateSalary())
            });
        }
    }
    
    private void calculateAllSalaries() {
        List<Employee> employees = payrollService.getAllEmployees();
        if (employees.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No employees found! Add some employees first.");
            return;
        }
        
        double total = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("💰 SALARY CALCULATION\n");
        sb.append("===================\n");
        
        for (Employee emp : employees) {
            double salary = emp.calculateSalary();
            total += salary;
            sb.append(String.format("%s: ₹%.2f\n", emp.getName(), salary));
        }
        
        sb.append("===================\n");
        sb.append(String.format("TOTAL: ₹%.2f\n", total));
        
        JOptionPane.showMessageDialog(this, sb.toString(), "Salary Calculation", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void filterBySalaryRange(double min, double max, JTextArea output) {
        output.setText("");
        List<Employee> filtered = payrollService.getEmployeesBySalaryRange(min, max);
        
        if (filtered.isEmpty()) {
            output.append("No employees found in salary range " + min + " - " + max + "\n");
            return;
        }
        
        output.append("Employees with salary between " + min + " and " + max + ":\n");
        output.append("==========================================\n");
        
        for (Employee emp : filtered) {
            output.append(String.format("%s - %s - %.2f\n",
                emp.getEmployeeId(), emp.getName(), emp.calculateSalary()));
        }
    }
    
    private void groupEmployeesByType(JTextArea output) {
        output.setText("");
        Map<String, List<Employee>> grouped = payrollService.groupEmployeesByType();
        
        if (grouped.isEmpty()) {
            output.append("No employees found.\n");
            return;
        }
        
        output.append("Employees Grouped by Type:\n");
        output.append("==========================\n");
        
        grouped.forEach((type, employees) -> {
            output.append("\n" + type + " Employees:\n");
            for (Employee emp : employees) {
                output.append(String.format("  %s - %.2f\n", emp.getName(), emp.calculateSalary()));
            }
        });
    }
    
    private void calculateTotalPayroll(JTextArea output) {
        double total = payrollService.calculateTotalPayroll();
        int employeeCount = payrollService.getAllEmployees().size();
        
        output.setText("💰 TOTAL PAYROLL CALCULATION\n");
        output.append("=========================\n");
        
        if (employeeCount == 0) {
            output.append("No employees found.\n");
            return;
        }
        
        output.append("Total Employees: " + employeeCount + "\n");
        output.append(String.format("Total Monthly Payroll: ₹%.2f\n", total));
        output.append(String.format("Total Annual Payroll: ₹%.2f\n", total * 12));
        output.append(String.format("Average Salary: ₹%.2f\n", total / employeeCount));
    }
    
    private void showPayrollStatistics(JTextArea output) {
        Map<String, Object> stats = payrollService.getPayrollStatistics();
        int employeeCount = (int) stats.get("totalEmployees");
        
        output.setText("📊 PAYROLL STATISTICS\n");
        output.append("===================\n");
        
        if (employeeCount == 0) {
            output.append("No employees found.\n");
            return;
        }
        
        output.append("Total Employees: " + stats.get("totalEmployees") + "\n");
        output.append(String.format("Total Payroll: ₹%.2f\n", stats.get("totalPayroll")));
        output.append(String.format("Average Salary: ₹%.2f\n", stats.get("averageSalary")));
        output.append(String.format("Highest Salary: ₹%.2f\n", stats.get("maxSalary")));
        output.append(String.format("Lowest Salary: ₹%.2f\n", stats.get("minSalary")));
    }
    
    private void generateSinglePayslip(JTextArea output) {
        List<Employee> employees = payrollService.getAllEmployees();
        if (employees.isEmpty()) {
            output.setText("No employees available! Add employees first.\n");
            return;
        }
        
        Employee emp = employees.get(0);
        try {
            payrollService.generatePayslip(emp.getEmployeeId(), 5000, 500, 200);
            output.setText("✅ PAYSLIP GENERATED!\n\n");
            output.append("Employee: " + emp.getName() + "\n");
            output.append("Employee ID: " + emp.getEmployeeId() + "\n");
            output.append("Payslip saved locally in 'payslips' folder\n");
        } catch (IOException ex) {
            output.setText("Error generating payslip: " + ex.getMessage());
        }
    }
    
    private void generateMultiplePayslips(JTextArea output) {
        List<Employee> employees = payrollService.getAllEmployees();
        
        if (employees.isEmpty()) {
            output.setText("No employees available! Add employees first.\n");
            return;
        }
        
        output.setText("🔄 GENERATING PAYSLIPS FOR ALL EMPLOYEES...\n\n");
        
        for (Employee emp : employees) {
            try {
                payrollService.generatePayslip(emp.getEmployeeId(), 5000, 500, 200);
                output.append("✅ Generated payslip for: " + emp.getName() + "\n");
            } catch (IOException ex) {
                output.append("❌ Error for " + emp.getName() + ": " + ex.getMessage() + "\n");
            }
        }
        
        output.append("\n🎉 Payslip generation completed!\n");
        output.append("All payslips saved in 'payslips' folder");
    }
    
    private void listPayslipFiles(JTextArea output) {
        List<String> files = FileManager.getPayslipFiles();
        output.setText("📄 GENERATED PAYSLIP FILES\n");
        output.append("======================\n");
        
        if (files.isEmpty()) {
            output.append("No payslip files found.\n");
            output.append("Generate payslips first using the buttons above.");
        } else {
            for (String file : files) {
                output.append("• " + file + "\n");
            }
        }
    }
    
    private void serializePayrollData(JTextArea output) {
        try {
            List<Employee> employees = payrollService.getAllEmployees();
            
            if (employees.isEmpty()) {
                output.setText("No employees to backup! Add employees first.\n");
                return;
            }
            
            StringBuilder backupData = new StringBuilder();
            backupData.append("=== PAYROLL BACKUP DATA ===\n");
            backupData.append("Backup Date: " + java.time.LocalDateTime.now() + "\n");
            backupData.append("Total Employees: " + employees.size() + "\n");
            backupData.append("System Mode: " + (payrollService.isApiEnabled() ? "ONLINE" : "OFFLINE") + "\n\n");
            
            for (Employee emp : employees) {
                backupData.append("EMPLOYEE: " + emp.getName() + "\n");
                backupData.append("  ID: " + emp.getEmployeeId() + "\n");
                backupData.append("  Type: " + emp.getEmployeeType() + "\n");
                backupData.append("  Email: " + emp.getEmail() + "\n");
                backupData.append("  Department: " + emp.getDepartment() + "\n");
                backupData.append("  Salary: " + emp.calculateSalary() + "\n");
                backupData.append("------------------------\n");
            }
            
            String filename = "payroll_backup_" + System.currentTimeMillis() + ".txt";
            try (java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(filename))) {
                writer.write(backupData.toString());
            }
            
            output.setText("✅ DATA BACKUP SUCCESSFUL!\n\n");
            output.append("File: " + filename + "\n");
            output.append("Total employees: " + employees.size() + "\n");
            output.append("Mode: " + (payrollService.isApiEnabled() ? "ONLINE" : "OFFLINE") + "\n");
            output.append("Backup saved as text file - Easy to read!\n");
            
        } catch (Exception ex) {
            output.setText("Error backing up payroll: " + ex.getMessage() + "\n");
            output.append("But don't worry - your data is still safe locally!");
        }
    }
}
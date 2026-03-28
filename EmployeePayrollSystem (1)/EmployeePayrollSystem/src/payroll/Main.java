package payroll;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create and show GUI
        SwingUtilities.invokeLater(() -> {
            PayrollSystemGUI gui = new PayrollSystemGUI();
            gui.setVisible(true);
        
        });
    }
}
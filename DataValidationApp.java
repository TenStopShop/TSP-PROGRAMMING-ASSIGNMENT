package javaapplication14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class DataValidationApp extends JFrame {
    JTextField nameField;
    JTextField emailField;
    JTextField phoneField;
    JPasswordField passwordField;
    JLabel feedbackLabel;

    public DataValidationApp() {
        setTitle("Data Validation System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        setVisible(true);
    }

    void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        // Name field
        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        nameField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                validateName();
            }
        });
        panel.add(nameField);

        // Email field
        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        emailField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                validateEmail();
            }
        });
        panel.add(emailField);

        // Phone number field
        panel.add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        phoneField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                validatePhone();
            }
        });
        panel.add(phoneField);

        // Password field
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        passwordField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                validatePassword();
            }
        });
        panel.add(passwordField);

        // Feedback label
        feedbackLabel = new JLabel();
        feedbackLabel.setForeground(Color.RED);
        panel.add(feedbackLabel);

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            if (isValidInput()) {
                JOptionPane.showMessageDialog(this, "All inputs are valid!");
            } else {
                JOptionPane.showMessageDialog(this, "Please correct the errors.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(submitButton);

        add(panel);
    }

    void validateName() {
        String name = nameField.getText();
        if (name.length() < 3) {
            feedbackLabel.setText("Name must be at least 3 characters long.");
        } else {
            feedbackLabel.setText("");
        }
    }

    void validateEmail() {
        String email = emailField.getText();
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!email.matches(emailRegex)) {
            feedbackLabel.setText("Invalid email format.");
        } else {
            feedbackLabel.setText("");
        }
    }

    void validatePhone() {
        String phone = phoneField.getText();
        if (phone.length() != 10 || !phone.matches("\\d+")) {
            feedbackLabel.setText("Phone number must be 10 digits long.");
        } else {
            feedbackLabel.setText("");
        }
    }

    void validatePassword() {
        String password = new String(passwordField.getPassword());
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        if (!password.matches(passwordRegex)) {
            feedbackLabel.setText("Password must include at least one uppercase letter, one lowercase letter, and one digit.");
        } else {
            feedbackLabel.setText("");
        }
    }

    boolean isValidInput() {
        return nameField.getText().length() >= 3 &&
               emailField.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$") &&
               phoneField.getText().length() == 10 &&
               new String(passwordField.getPassword()).matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DataValidationApp());
    }
}

package com.AavengersTrafficControle.trafficsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        // TODO: Implement actual authentication logic
        if (username.equals("admin") && password.equals("admin")) {
            // For now, just close the login window
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.close();
        } else {
            // TODO: Show error message
            System.out.println("Invalid credentials");
        }
    }
    
    @FXML
    private void handleCancel() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }
} 
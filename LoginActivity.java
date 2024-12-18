package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        Button btnCreateAccount = findViewById(R.id.btnCreateAccount);

        // Handle login submission
        btnSubmit.setOnClickListener(v -> {
            EditText usernameField = findViewById(R.id.username);
            EditText passwordField = findViewById(R.id.password);

            String username = usernameField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();

            // Check if both username and password are provided
            if (username.isEmpty() || password.isEmpty()) {
                // Display an error if either username or password is missing
                Toast.makeText(LoginActivity.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            } else {
                // Simulate a successful login if both fields are filled
                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                // Navigate to SmsPermissionActivity
                Intent intent = new Intent(LoginActivity.this, SmsPermissionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Handle account creation (Navigate to CreateAccountActivity)
        btnCreateAccount.setOnClickListener(v -> {
            // Navigate to account creation screen
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
        });
    }
}
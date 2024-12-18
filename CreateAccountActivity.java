package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Button btnCreate = findViewById(R.id.btnCreate);

        // Handle account creation
        btnCreate.setOnClickListener(v -> {
            EditText usernameField = findViewById(R.id.username);
            EditText passwordField = findViewById(R.id.password);
            EditText emailField = findViewById(R.id.email);

            String username = usernameField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            String email = emailField.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                Toast.makeText(CreateAccountActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Simulate account creation
                Toast.makeText(CreateAccountActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();

                // Optionally, navigate back to the login screen
                finish();
            }
        });
    }
}

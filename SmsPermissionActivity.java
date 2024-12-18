package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class SmsPermissionActivity extends AppCompatActivity {

    private static final int SMS_PERMISSION_REQUEST_CODE = 1;
    private boolean isPermissionRequested = false; // Flag to track permission request state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_permission);

        Button btnRequestPermission = findViewById(R.id.btnRequestSms);

        // Set up button click listener for requesting permission
        btnRequestPermission.setOnClickListener(v -> {
            if (!isPermissionRequested) {
                requestSmsPermission();
            } else {
                Toast.makeText(this, "Permission already requested. Please grant it.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to request SMS permission
    private void requestSmsPermission() {
        isPermissionRequested = true;
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_REQUEST_CODE);
    }

    // Handle the result of the permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == SMS_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, allow the user to send the SMS
                sendSmsAndNavigate("1234567890", "Hello, this is a test message!");
            } else {
                // Permission denied
                Toast.makeText(this, "Permission to send SMS was denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Send SMS and navigate to GridActivity
    private void sendSmsAndNavigate(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(this, "SMS sent! Redirecting to data grid...", Toast.LENGTH_SHORT).show();

            // Navigate to GridActivity
            Intent intent = new Intent(SmsPermissionActivity.this, GridActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "Failed to send SMS. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}
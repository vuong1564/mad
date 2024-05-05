package com.example.medicinemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if ("1".equals(username) && "1".equals(password)) {
                    Toast.makeText(LoginActivity.this, "Successful login", Toast.LENGTH_SHORT).show();


                    // Create an Intent to start the MainActivity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    // Start the MainActivity
                    startActivity(intent);

                    // Close the LoginActivity
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "Login failure", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
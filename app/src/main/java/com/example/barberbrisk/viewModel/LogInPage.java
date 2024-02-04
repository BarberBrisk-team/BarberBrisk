package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;
import com.example.barberbrisk.model.LoginModel;

public class LogInPage extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;
    private Button loginButton;

    private LoginModel loginModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);

        emailField = findViewById(R.id.editTextTextEmailAddress2);
        passwordField = findViewById(R.id.editTextTextPassword);
        loginButton = findViewById(R.id.button);

        loginModel = new LoginModel();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();
                Log.d("AuthTesting", "email: " + email + " password " + password);
                loginModel.loginUser(email, password, new LoginModel.OnUserLoggedInListener() {
                    @Override
                    public void onUserLoggedIn(String userType) {
                        if ("Customer".equals(userType)) {
                            goHomeCustomer();
                        } else if ("Barber".equals(userType)) {
                            goHomeBarber();
                        }
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Toast.makeText(LogInPage.this, "Authentication failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    public void goHomeBarber() {
        startActivity(new Intent(LogInPage.this, appointment_order.class));
    }
    public void goHomeCustomer() {
        startActivity(new Intent(LogInPage.this, appointment_order.class));
    }
}

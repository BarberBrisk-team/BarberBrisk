package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;
import com.example.barberbrisk.model.LoginModel;

/**
 * This class represents the login page of the application.
 * It handles user interactions such as entering email and password, and clicking the login button.
 * It uses a LoginModel to perform the actual authentication process.
 */
public class LogInPage extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;
    private Intent intent;
    private static LoginModel loginModel;
    /**
     * This method is called when the login activity is starting.
     * It initializes the activity, sets the content view, and sets up the login process.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);

        emailField = findViewById(R.id.editTextTextEmailAddress2);
        passwordField = findViewById(R.id.editTextTextPassword);
        Button loginButton = findViewById(R.id.button);

        loginModel = new LoginModel();

        /*
          Sets an OnClickListener for the login button.
          When the button is clicked, it gets the entered email and password, and calls the loginUser method of the LoginModel.
         */
        loginButton.setOnClickListener(v -> {
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();
//          transfer thw email of the user to anther process
            Intent i = getIntent();
            i.putExtra("email", email);
            i.putExtra("password", password);

            Log.d("AuthTesting", "email: " + email + " password " + password);
            loginModel.loginUser(email, password, new LoginModel.OnUserLoggedInListener() {
                /**
                 * This method is called when the user is successfully logged in.
                 * It checks the user type and calls the appropriate method to go to the home page.
                 */
                @Override
                public void onUserLoggedIn(String userType) {
                    if ("Client".equals(userType)) {
                        Toast.makeText(LogInPage.this, "Loading Client Home page: ", Toast.LENGTH_SHORT).show();
                        goHomeCustomer(loginModel.getUid());
                    } else if ("Barber".equals(userType)) {
                        Toast.makeText(LogInPage.this, "Loading Barber Home page: ", Toast.LENGTH_SHORT).show();
                        goHomeBarber(loginModel.getUid());
                    }
                }

                /**
                 * This method is called when the login process fails.
                 * It displays a toast message with the error message.
                 */
                @Override
                public void onFailure(String errorMessage) {
                    Toast.makeText(LogInPage.this, "Authentication failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    /**
     * This method is called to go to the barber home page.
     * It starts the BarberHomePage activity.
     */
    public void goHomeBarber(String Uid) {
        intent = new Intent(LogInPage.this, barberHomePage.class);
        intent.putExtra("Uid", Uid);
        startActivity(intent);
    }

    /**
     * This method is called to go to the customer home page.
     * It starts the ClientHomePage activity.
     */
    public void goHomeCustomer(String Uid) {
        intent = new Intent(LogInPage.this, clientHomePage.class);
        intent.putExtra("Uid", Uid);
        startActivity(intent);
    }

}


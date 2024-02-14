package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.DB.DataBase;
import com.example.barberbrisk.R;
import com.example.barberbrisk.model.RateNotificationManager;
import com.google.firebase.FirebaseApp;
//import com.example.barberbrisk.model.entranceModel;

public class EntrancePage extends AppCompatActivity {
//    private entranceModel em;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enterance_page);
        Button signInButton = findViewById(R.id.Sign_in_button);
        FirebaseApp.initializeApp(this);
        DataBase.DownladBarberList();
        // Set a click listener on the "Sign in" button
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the Login activity
                Intent intent = new Intent(EntrancePage.this, LogInPage.class);

                // Start the Login activity
                startActivity(intent);
            }
        });

        // Find the "Sign up" button by its ID
        Button signUpButton = findViewById(R.id.Sign_up_button);

        // Set a click listener on the "Sign up" button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the SignUp activity
                Intent intent = new Intent(EntrancePage.this, signup.class);

//                //Todo: remove those lines
//                RateNotificationManager rateNotificationManager = new RateNotificationManager();
//                rateNotificationManager.sendRateNotification(getApplicationContext(),"dUILdwEj8tgGuP0y4W4GYP9XkvU2", "clientID", "appointmentID");
//                //Todo: remove until here


                // Start the SignUp activity
                 startActivity(intent);
            }
        });
    }
}



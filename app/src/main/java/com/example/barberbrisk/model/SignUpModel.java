package com.example.barberbrisk.model;

import android.util.Log;
import android.widget.Toast;

import com.example.barberbrisk.DB.AuthenticationDB;
import com.example.barberbrisk.DB.DataBase;
import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.objects.Client;
import com.example.barberbrisk.viewModel.signup;

public class SignUpModel {
    signup activity;
    AuthenticationDB authDb = new AuthenticationDB();

    public SignUpModel(signup activity) {
        this.activity = activity;

    }

    public boolean validatePassword(String enteredPassword) {
        String barbersPassword = "1994";
        return enteredPassword.equals(barbersPassword);
    }

    public void registerNewUser(String email, String password, String name, String phone) {

        authDb.registerNewUser(email, password, task -> {
            if (task.isSuccessful()) {
                Log.d("RegisterUser", "User registration successful");
                if (activity.barberCheckBox.isChecked()) {
                    if (validatePassword(activity.additionalPasswordEditText.getText().toString())) {
                        // Log statements for debugging
                        Log.d("RegisterUser", "Barber registration successful");
                        Barber barber = new Barber(authDb.getUID(), name, email, phone, password);
                        DataBase.NewBarberDB(barber);
                        activity.goHomeBarber(authDb.getUID());
                    } else {
                        // Log statements for debugging
                        Log.e("RegisterUser", "Invalid additional password for barber");
                        Toast.makeText(activity, "Invalid additional password for barber", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Log statements for debugging
                    Log.d("RegisterUser", "Client registration successful");
                    Client client = new Client(authDb.getUID(), name, email, phone, password);
                    DataBase.NewClientDB(client);
                    activity.goHomeClient(authDb.getUID());
                }
            } else {
                // Log statements for debugging
                Log.e("RegisterUser", "User registration failed: " + task.getException());
            }
        });
    }

}





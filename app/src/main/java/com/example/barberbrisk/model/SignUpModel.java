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
    AuthenticationDB authdb = new AuthenticationDB();
    public SignUpModel(signup activity) {
        this.activity = activity;

    }

    public boolean validatePassword(String enteredPassword) {
        String barbersPassword = "1994";
        return enteredPassword.equals(barbersPassword);
    }

    public void registerNewUser(String email, String password, String name, String phone) {

        authdb.registerNewUser(email, password, task -> {
            if (task.isSuccessful()) {
                Log.d("RegisterUser", "User registration successful");
                if (activity.barberCheckBox.isChecked()) {
                    if (validatePassword(activity.additionalPasswordEditText.getText().toString())) {
                        // Log statements for debugging
                        Log.d("RegisterUser", "Barber registration successful");
                        Barber barber = new Barber(authdb.getUID(), email, password, name, phone);
                        DataBase.NewBarberDB(barber);
                        activity.goHomeBarber(authdb.getUID());
                    } else {
                        // Log statements for debugging
                        Log.e("RegisterUser", "Invalid additional password for barber");
                        Toast.makeText(activity, "Invalid additional password for barber" , Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Log statements for debugging
                    Log.d("RegisterUser", "Client registration successful");
                    Client client = new Client(authdb.getUID(), email, password, name, phone);
                    DataBase.NewClientDB(client);
                    activity.goHomeClient(authdb.getUID());
                }
            } else {
                // Log statements for debugging
                Log.e("RegisterUser", "User registration failed: " + task.getException());
            }
        });
    }

}





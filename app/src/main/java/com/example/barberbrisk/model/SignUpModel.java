package com.example.barberbrisk.model;

import android.util.Log;

import com.example.barberbrisk.DB.FirebaseDB;
import com.example.barberbrisk.viewModel.signup;

public class SignUpModel {
    signup activity;
    FirebaseDB db = new FirebaseDB();

    public SignUpModel(signup activity) {
        this.activity = activity;

    }

    public boolean validatePassword(String enteredPassword) {
        String barbersPassword = "1994";
        return enteredPassword.equals(barbersPassword);
    }

    public void registerNewUser(String email, String password, String name, String phone) {
        db.registerNewUser(email, password, task -> {
            if (task.isSuccessful()) {
                Log.d("RegisterUser", "User registration successful");
                if (activity.barberCheckBox.isChecked()) {
                    if (validatePassword(activity.additionalPasswordEditText.getText().toString())) {
                        // Log statements for debugging
                        Log.d("RegisterUser", "Barber registration successful");
//                        Barber barber = new Barber(db.getUID(), email, phone, password);
                        db.putNewBar(db.getUID(), email, password, name, phone);
                        activity.goHomeBarber();
                    } else {
                        // Log statements for debugging
                        Log.e("RegisterUser", "Invalid additional password for barber");
                        /* todo: add error message */
                    }
                } else {
                    // Log statements for debugging
                    Log.d("RegisterUser", "Client registration successful");
                    db.putNewClient(db.getUID(), email, password, name, phone);
                    activity.goHomeClient();
                }
            } else {
                // Log statements for debugging
                Log.e("RegisterUser", "User registration failed: " + task.getException());
            }
        });
    }

}





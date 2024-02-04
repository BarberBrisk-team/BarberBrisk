package com.example.barberbrisk.model;


import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.viewModel.signup;

public class SignUpModel {
    private final String barbersPassword = "1994";
    signup activity;

    public SignUpModel(signup activity) {
        this.activity = activity;

    }

    public boolean validatePassword(String enteredPassword) {
        return enteredPassword.equals(barbersPassword);
    }

    public void registerNewUser(String email, String password, String name, String phone) {
        if (activity.barberCheckBox.isChecked()) {
            if (validatePassword(activity.additionalPasswordEditText.getText().toString())){
//                Barber barber = new Barber(name,email,phone,password);
//                NewBarber(name ,"",phone,)

            }
        }
    }
}

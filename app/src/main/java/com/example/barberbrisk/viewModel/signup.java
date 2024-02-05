//package com.example.barberbrisk.viewModel;
////activity_signup
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.barberbrisk.R;
//import com.example.barberbrisk.model.SignUpModel;
//
//public class signup extends AppCompatActivity {
//
//    public CheckBox barberCheckBox;
//    private LinearLayout linearPasswordContainer;
//    public EditText additionalPasswordEditText;
//    private SignUpModel model;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
//        SignUpModel model = new SignUpModel(this);
//
//
//
//        super.onStart();
//        barberCheckBox = findViewById(R.id.checkBox);
//        linearPasswordContainer = findViewById(R.id.linearPasswordContainer);
//        additionalPasswordEditText = findViewById(R.id.additionalPasswordEditText);
//        // Initially hide the additional password-related fields
//        linearPasswordContainer.setVisibility(View.GONE);
//        Button submitButton = findViewById(R.id.submit_button);
//
//        barberCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if (isChecked) {
//                // Show additional password-related fields
//                linearPasswordContainer.setVisibility(View.VISIBLE);
//            } else {
//                // Hide additional password-related fields
//                linearPasswordContainer.setVisibility(View.GONE);
//            }
//        });
//
//        submitButton.setOnClickListener(view -> {
//            // Perform signup logic here
//            // You can check the checkbox state to determine the type of user
//            EditText name = findViewById(R.id.editTextName);
//            EditText email = findViewById(R.id.editTextTextEmailAddress);
//            EditText password = findViewById(R.id.editTextTextPassword2);
//            EditText phone = findViewById(R.id.editTextPhone2);
//            model.registerNewUser(email.getText().toString(), password.getText().toString(), name.getText().toString(), phone.getText().toString());
//        });
//    }
//    public void goHomeVolunteer() {
//        startActivity(new Intent(signup.this,LogInPage.class));
//    }
//
//}

package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;
import com.example.barberbrisk.model.SignUpModel;

public class signup extends AppCompatActivity {

    public CheckBox barberCheckBox;
    private LinearLayout linearPasswordContainer;
    public EditText additionalPasswordEditText;
    private SignUpModel model;
    Button submitButton;
    EditText Editname, Editemail, Editpassword, Editphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        model = new SignUpModel(this);
        Editname = findViewById(R.id.editTextName);
        Editemail= findViewById(R.id.editTextTextEmailAddress);
        Editpassword = findViewById(R.id.editTextTextPassword2);
        Editphone = findViewById(R.id.editTextPhone2);
        barberCheckBox = findViewById(R.id.checkBox);
        linearPasswordContainer = findViewById(R.id.linearPasswordContainer);
        additionalPasswordEditText = findViewById(R.id.additionalPasswordEditText);
        // Initially hide the additional password-related fields
        linearPasswordContainer.setVisibility(View.GONE);

        barberCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show additional password-related fields
                linearPasswordContainer.setVisibility(View.VISIBLE);
            } else {
                // Hide additional password-related fields
                linearPasswordContainer.setVisibility(View.GONE);
            }
        });
        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> {
            Log.d("on pressed","onClick: ");
            String email, password, name, phone;
            email = Editemail.getText().toString();
            password = Editpassword.getText().toString();
            name = Editname.getText().toString();
            phone = Editphone.getText().toString();

            if (email.isEmpty() || password.isEmpty() || name.isEmpty() || phone.isEmpty()){
                Toast.makeText(this, "One or many of your details are empty, please fill them" , Toast.LENGTH_SHORT).show();
            }
            else if(!model.validatePassword(additionalPasswordEditText.getText().toString())){
                Toast.makeText(this, "Invalid additional password for barber" , Toast.LENGTH_SHORT).show();
            }
            else {
                // Perform signup logic here
                // You can check the checkbox state to determine the type of user
                model.registerNewUser(email, password, name, phone);
            }
        });
    }


        public void goHomeClient() {
            startActivity(new Intent(signup.this, clientHomePage.class));
        }
        public void goHomeBarber() {
            startActivity(new Intent(signup.this, barberHomePage.class));
        }

        // Example method for showing a toast message
        private void showToast (String message){
            // You can implement your own toast method or use a library like Toasty
            // For simplicity, I'm using the default Toast here
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

        // Example method for validating the additional password
        private boolean validatePassword (String password){
            // Implement your own password validation logic
            // For now, I'm just checking if the password is not empty
            return !TextUtils.isEmpty(password);
        }
    }

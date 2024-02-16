package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Bundle;
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
        Editemail = findViewById(R.id.editTextTextEmailAddress);
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
            Log.d("on pressed", "onClick: ");
            String email, password, name, phone;
            email = Editemail.getText().toString();
            password = Editpassword.getText().toString();
            name = Editname.getText().toString();
            phone = Editphone.getText().toString();

            if (email.isEmpty() || password.isEmpty() || name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "One or many of your details are empty, please fill them", Toast.LENGTH_SHORT).show();
            }
            else {
                model.registerNewUser(email, password, name, phone);
            }
        });
    }


    public void goHomeClient(String Uid) {
        Intent intent = new Intent(signup.this, clientHomePage.class);
        intent.putExtra("Uid", Uid);
        startActivity(intent);
    }

    public void goHomeBarber(String Uid) {
        Intent intent = new Intent(signup.this, barberHomePage.class);
        intent.putExtra("Uid", Uid);
        startActivity(intent);
    }
}

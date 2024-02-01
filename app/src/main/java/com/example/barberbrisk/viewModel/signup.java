package com.example.barberbrisk.viewModel;
//activity_signup
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;
import com.example.barberbrisk.model.SignUpModel;

public class signup extends AppCompatActivity {

    public CheckBox barberCheckBox;
    private LinearLayout linearPasswordContainer;
    public EditText additionalPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        SignUpModel model = new SignUpModel(this);

        barberCheckBox = findViewById(R.id.checkBox);
        linearPasswordContainer = findViewById(R.id.linearPasswordContainer);
        additionalPasswordEditText = findViewById(R.id.additionalPasswordEditText);
        // Initially hide the additional password-related fields
        linearPasswordContainer.setVisibility(View.GONE);
        Button submitButton = findViewById(R.id.submit_button);

        barberCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show additional password-related fields
                linearPasswordContainer.setVisibility(View.VISIBLE);
            } else {
                // Hide additional password-related fields
                linearPasswordContainer.setVisibility(View.GONE);
            }
        });

        submitButton.setOnClickListener(view -> {
            // Perform signup logic here
            // You can check the checkbox state to determine the type of user
            EditText name =  findViewById(R.id.editTextName);
            EditText email =  findViewById(R.id.editTextTextEmailAddress);
            EditText password = findViewById(R.id.editTextTextPassword2);
            EditText phone = findViewById(R.id.editTextPhone2);
            boolean isBarber = barberCheckBox.isChecked();
            // If isBarber is true, retrieve additional password from additionalPasswordEditText
            String additionalPassword = isBarber ? additionalPasswordEditText.getText().toString() : "";

            // Now, you can proceed with your signup logic
        });
    }
}

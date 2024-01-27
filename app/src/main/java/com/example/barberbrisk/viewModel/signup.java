package com.example.barberbrisk.viewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.FrameLayout;

import com.example.barberbrisk.R;

public class signup extends AppCompatActivity {
    CheckBox showPasswordCheckbox = findViewById(R.id.checkBox);
    FrameLayout passwordPlaceholder = findViewById(R.id.passwordPlaceholder);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }
}
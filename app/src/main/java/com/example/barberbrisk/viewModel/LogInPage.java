package com.example.barberbrisk.viewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.barberbrisk.R;
import com.example.barberbrisk.objects.User;


public class LogInPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);
        User user = new User();
    }
}
package com.example.barberbrisk;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class confirmation_code extends AppCompatActivity {
    TextView timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_code);

        timer = findViewById(R.id.timer);

        new CountDownTimer(100000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText("Time left " +millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {

            }
        }.start();
    }
}
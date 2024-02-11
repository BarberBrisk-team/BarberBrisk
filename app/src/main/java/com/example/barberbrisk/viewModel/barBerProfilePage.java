package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;
import com.example.barberbrisk.objects.Barber;

public class barBerProfilePage extends AppCompatActivity {
    private Intent barberIntent;
    private Barber myBarber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_ber_profile_page);
        Log.d("barberProfile", "start Barber profile activity");

//      creat a obj of the first activity and take data about the user.
        barberIntent = getIntent();
        myBarber = barberIntent.getParcelableExtra("myObj");

//        check if the data arrive exactly right
        Log.d("name", myBarber.getName().toString());
        Log.d("email", myBarber.getEmail().toString());
        Log.d("phone", myBarber.getPhone().toString());

//        set data on the activity
        Button b1 = (Button) findViewById(R.id.buttonNameBarber);
        Button b2 = (Button) findViewById(R.id.buttonEmailBarber);
        Button b3 = (Button)findViewById(R.id.buttonPhoneBarber);
        b1.setText(myBarber.getName());
        b2.setText(myBarber.getEmail());
        b3.setText(myBarber.getPhone());
    }
    public void arrowBackButton(View v){
        Intent i = new Intent(barBerProfilePage.this, barberHomePage.class);
        startActivity(i);
    }
}
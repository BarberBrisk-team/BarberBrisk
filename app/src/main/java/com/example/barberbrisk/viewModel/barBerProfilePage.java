package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class barBerProfilePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_ber_profile_page);
        Log.d("test", "start Barber profile activity");

//      creat a obj of the first activity and take data about the user.
        Intent i = getIntent();
        String tmp = "NAME";
        String tmp2 = "EMAIL";
        String tmp3 = "PHONE";
        String name = i.getStringExtra(tmp);
        String email = i.getStringExtra(tmp2);
        String phone = i.getStringExtra(tmp3);

//        check if the data arrive exactly right
        Log.d("name", name.toString());
        Log.d("email", email.toString());
        Log.d("phone", phone.toString());

//        set data on the activity
        Button b1 = (Button) findViewById(R.id.buttonNameBarber);
        Button b2 = (Button) findViewById(R.id.buttonEmailBarber);
        Button b3 = (Button)findViewById(R.id.buttonPhoneBarber);
        b1.setText(name);
        b2.setText(email);
        b3.setText(phone);
    }
    public void arrowBackButton(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
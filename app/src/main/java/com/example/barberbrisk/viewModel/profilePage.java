package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class profilePage extends AppCompatActivity {

    public void arrowBackButton(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

//      write a message on the screen that the activuty start
        Log.d("test", "start profile activity");

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
        Button b1 = (Button) findViewById(R.id.buttonName);
        Button b2 = (Button) findViewById(R.id.buttonEmail);
        Button b3 = (Button)findViewById(R.id.buttonPhone);
        b1.setText(name);
        b2.setText(email);
        b3.setText(phone);
    }
}
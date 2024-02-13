package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;
import com.example.barberbrisk.objects.Client;

public class clientProfilePage extends AppCompatActivity {
    private Intent clientIntent;
    private Client myClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile_page);

//      write a message on the screen that the activuty start
        Log.d("בךןקמאProfile", "start profile activity");

//      creat a obj of the first activity and take data about the user.
        clientIntent = getIntent();
        String BarberUid = clientIntent.getStringExtra("Uid");

//      check if the data arrive exactly right
//        Log.d("name", myClient.getName().toString());
//        Log.d("email", myClient.getEmail().toString());
//        Log.d("phone", myClient.getPhone().toString());

//        set data on the activity
        Button b1 = (Button) findViewById(R.id.buttonName);
        Button b2 = (Button) findViewById(R.id.buttonEmail);
        Button b3 = (Button)findViewById(R.id.buttonPhone);
//        b1.setText(myClient.getName());
//        b2.setText(myClient.getEmail());
//        b3.setText(myClient.getPhone());
    }

    public void arrowBackButton(View v){
        Intent i = new Intent(this, clientHomePage.class);
        startActivity(i);
    }
}
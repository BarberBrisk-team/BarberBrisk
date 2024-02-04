package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;
import com.example.barberbrisk.objects.Client;

public class clientHomePage extends AppCompatActivity {
    private Client myObj;
    private Intent myIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home_page);
        myIntent = getIntent();
        //      we need to fill all the object fields by the DB
        String email = myIntent.getStringExtra("email");
        String password = myIntent.getStringExtra("password");
//        myObj = new Client()

    }
    public void handelButtonProfile(View v){
        myIntent.putExtra("myobj",myObj);
        startActivity(new Intent(clientHomePage.this, clientProfilePage.class));
    }

    public void handelButtonOrder(View n){
        myIntent.putExtra("myobj",myObj);
        startActivity(new Intent(clientHomePage.this, appointment_order.class));
    }
}
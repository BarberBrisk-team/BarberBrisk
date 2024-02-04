package com.example.barberbrisk.tests;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.DB.DataBase;
import com.example.barberbrisk.objects.Appointment;
import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.objects.Client;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {
    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<Barber> barbers = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
        DataBase.NewBarberDB(new Barber("44444333", "Barber", "avi@aff.com", "0544444444", "123456"));
//  DataBase.GenerateBarberAppointments("333333");
//        while(clients.isEmpty());
        Log.d("AllClients", String.valueOf(clients.size()));
    }





}
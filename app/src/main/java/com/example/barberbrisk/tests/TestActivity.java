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

import java.sql.Timestamp;
import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {
    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<Barber> barbers = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataBase.NewBarberDB(new Barber("UIDAhla", "Ahla Gever", "ahla@gnu.com", "051212398", "1234567"));
        super.onCreate(savedInstanceState);
        DataBase.NewBarberDB(new Barber("44444444","Sami Didi","hack@me.com","0503665987","3136665"));
//
  DataBase.GenerateBarberAppointments("333333");
//        while(clients.isEmpty());
        Log.d("AllClients", String.valueOf(clients.size()));
    }




    public void listener(ArrayList<Client> client)
    {
        this.clients = client;
//        Log.d("AllClients", "2 client: " + client.get(1).getFirstName());
    }
}
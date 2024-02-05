package com.example.barberbrisk.tests;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.DB.DataBase;
import com.example.barberbrisk.R;
import com.example.barberbrisk.model.AppointmentManegerModel;
import com.example.barberbrisk.objects.Appointment;
import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.objects.Client;
import com.example.barberbrisk.objects.ClientAppointment;

import java.util.ArrayList;
import java.util.Map;

public class TestActivity extends AppCompatActivity {
    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<Barber> barbers = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button button = findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AllClients","ok?");
                ClientAppointment clientAppointment = new ClientAppointment("3tsozkk2L1HExwuNXe3A","WOU9qzIpUfbzzHTnb94PmOPw0Mu2","7777");
                AppointmentManegerModel.setClientAppointment(clientAppointment);

            }
        });

          DataBase.DownladBarberList();
        DataBase.DownloadListAppoinment();
//
//        DataBase.NewBarberDB(new Barber("44444333", "Barber", "avi@aff.com", "0544444444", "123456"));
//  DataBase.GenerateBarberAppointments("333333");
//        while(clients.isEmpty());
//        DataBase.SetClientAppointment(new ClientAppointment("9RaMN6I7Q9xoarECw8dF","WOU9qzIpUfbzzHTnb94PmOPw0Mu2","4545"));
        Log.d("AllClients", String.valueOf(clients.size()));

//        Appointment appointment = new Appointment("1234", "2021-06-01 12:00:00", true);
//        Log.d("Appointment", appointment.getTimeAndDate().toString());
    }







}
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
import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.objects.Client;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class TestActivity extends AppCompatActivity {
    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<Barber> barbers = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        Button button = findViewById(R.id.button5);
//        Date date = new Date(2021, 6, 1);
//        Time time = new Time(12, 0, 0);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("AllClients","ok?");
//                boolean b =  AppointmentManegerModel.isAppointmentAvailable("WOU9qzIpUfbzzHTnb94PmOPw0Mu2","3tsozkk2L1HExwuNXe3A","7777",date,time);
//                Log.d("AllClients", "could create a appointment?" + String.valueOf(b));
//            }
//        });
//        Timestamp timestamp = new Timestamp(2021, 6, 1, 12, 0, 0, 0);
//        DataBase.BarberNewAppointment(new Appointment("WOU9qzIpUfbzzHTnb94PmOPw0Mu2",timestamp,true));
//        DataBase.DownloadBarberList();
//        DataBase.DownloadListAppoinment();
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
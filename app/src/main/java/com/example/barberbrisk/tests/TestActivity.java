package com.example.barberbrisk.tests;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.DB.DataBase;
import com.example.barberbrisk.objects.Client;
import com.example.barberbrisk.objects.User;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    List<User> clients = new ArrayList<>();
    public interface EventListener {
        void onTrigger();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        com.example.barberbrisk.DB.DataBase.NewBarber("Sami", "Uri","0503617555", new File(""));
//
//        com.example.barberbrisk.DB.DataBase.NewCustomer("koko", "hatba", "0315544423");
//        com.example.barberbrisk.DB.DataBase.NewCustomer("to", "yyy", "343434343");
//        com.example.barberbrisk.DB.DataBase.NewCustomer("gov", "errr", "9999999");
//        com.example.barberbrisk.DB.DataBase.CustomerArrangeAppointment("03123123123", "0503617555", null, null, "Marins");
//        com.example.barberbrisk.DB.DataBase.CustomerRating(4.5, "0503617555", "03123123123");

//          DataBase.ListOfCustomer();
          ArrayList<Barber> barbers = DataBase.ListOfBarbers();
        for (Barber bar : barbers) {
            Log.d("AllBarbers", bar.toString());
        }
    }


    public void litner()
    {
        Log.d("TestActivity1", "the data is " + clients.size());
    }

}
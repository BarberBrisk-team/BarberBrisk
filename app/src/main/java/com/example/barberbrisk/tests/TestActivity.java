package com.example.barberbrisk.tests;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.DB.DataBase;
import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.objects.Client;
import com.example.barberbrisk.objects.User;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    ArrayList<Client> clients = new ArrayList<>();
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
//        ArrayList<Barber> barbers = DataBase.ListOfBarbers();
//        Log.d("AllBarbers", ""+barbers.size());
//        for (Barber bar : barbers) {
//            Log.d("AllBarbers", bar.toString());
//        }



        DataBase.ListOfCustomer(clients1 -> {
            Log.d("AllBarbers", "clients1: " + clients1.size());
            Log.d("AllBarbers", "one client: " + clients1.get(0).getFirstName());
            litner(clients1);
        });

//        while(clients.isEmpty());
        Log.d("AllBarbers", "" + clients.size());
    }




    public void litner(ArrayList<Client> client)
    {
        this.clients = client;
        Log.d("AllBarbers", "2 client: " + client.get(1).getFirstName());
    }

}
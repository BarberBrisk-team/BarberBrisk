package com.example.barberbrisk.tests;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.DB.DataBase;
import com.example.barberbrisk.objects.Client;

import java.util.ArrayList;

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
            listener(clients1);
        });

//        while(clients.isEmpty());
        Log.d("AllBarbers", String.valueOf(clients.size()));
    }




    public void listener(ArrayList<Client> client)
    {
        this.clients = client;
        Log.d("AllBarbers", "2 client: " + client.get(1).getFirstName());
    }

}
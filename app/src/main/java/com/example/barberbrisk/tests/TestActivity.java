package com.example.barberbrisk.tests;

import android.os.Bundle;
import android.util.Log;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataBase.NewBarberDB(new Barber("UIDAhla", "Ahla Gever", "ahla@gnu.com", "051212398", "1234567"));
        super.onCreate(savedInstanceState);
        DataBase.NewBarberDB(new Barber("44444444","Sami Didi","hack@me.com","0503665987","3136665"));
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

//        DataBase.BarberNewAppointments(new Appointment("Q3rL9zpUMdTIAAMrBgp7Hp8Ab3r1",new Timestamp(2024,3,1,9,0,0,0),true));

//        while(clients.isEmpty());
        Log.d("AllClients", String.valueOf(clients.size()));
    }




    public void listener(ArrayList<Client> client)
    {
        this.clients = client;
//        Log.d("AllClients", "2 client: " + client.get(1).getFirstName());
    }
}
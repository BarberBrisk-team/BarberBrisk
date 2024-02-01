package com.example.barberbrisk.tests;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.DB.DataBase;
import com.example.barberbrisk.objects.Barber;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        com.example.barberbrisk.DB.DataBase.NewBarber("Sami", "Uri","0503617555", new File(""));
//        com.example.barberbrisk.DB.DataBase.NewCustomer("Ahla", "Gever", "03123123123");
//        com.example.barberbrisk.DB.DataBase.CustomerArrangeAppointment("03123123123", "0503617555", null, null, "Marins");
//        com.example.barberbrisk.DB.DataBase.CustomerRating(4.5, "0503617555", "03123123123");

//          DataBase.ListOfCustomer();
          ArrayList<Barber> barbers = DataBase.ListOfBarbers();
        for (Barber bar : barbers) {
            Log.d("AllBarbers", bar.toString());
        }
    }
}
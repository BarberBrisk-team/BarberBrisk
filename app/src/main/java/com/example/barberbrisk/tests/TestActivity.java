package com.example.barberbrisk.tests;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.DB.DataBase;

import java.io.File;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBase.NewBarber("Sami", "Uri","0503617555", new File(""));
        DataBase.NewCustomer("Ahla", "Gever", "03123123123");
        DataBase.CustomerArrangeAppointment("03123123123", "0503617555", null, null, "Marins");
        DataBase.CustomerRating(4.5, "0503617555", "03123123123");
    }
}
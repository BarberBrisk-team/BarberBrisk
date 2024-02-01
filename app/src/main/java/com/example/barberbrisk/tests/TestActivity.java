package com.example.barberbrisk.tests;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.barberbrisk.DB.DataBase;
import com.example.barberbrisk.R;

import com.example.barberbrisk.objects.DataBase;

import java.io.File;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        com.example.barberbrisk.objects.DataBase.NewBarber("Sami", "Uri","0503617555", new File(""));
//        com.example.barberbrisk.objects.DataBase.NewCustomer("Ahla", "Gever", "03123123123");
//        com.example.barberbrisk.objects.DataBase.CustomerArrangeAppointment("03123123123", "0503617555", null, null, "Marins");
//        com.example.barberbrisk.objects.DataBase.CustomerRating(4.5, "0503617555", "03123123123");

          DataBase.ListOfCustomer();
    }
}
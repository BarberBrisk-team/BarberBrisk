package com.example.barberbrisk.tests;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.DB.DataBase;
import com.example.barberbrisk.objects.Appointment;
import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.objects.Client;
import com.example.barberbrisk.objects.User;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TestActivity extends AppCompatActivity {
    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<Barber> barbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        DataBase.NewBarberDB(new Barber("44444444","Sami", "Didi","hack@me.com","0503665987","3136665"));
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


                // יצירת אובייקט Date ממחרוזת תאריך ושעה
                String dateStr = "7-11-11 12:13:14";
                // הגדרת פורמט התאריך והשעה
                SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
                // המרת המחרוזת לאובייקט Date
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        // המרת האובייקט Date לערך long
                long time = date.getTime();
                // יצירת אובייקט Timestamp מהערך long
                Timestamp timestamp = new Timestamp(time);
                // הדפסת האובייקט Timestamp
                System.out.println(timestamp);

                // יצירת מחרוזת תאריך ושעה בפורמט SQL
                String dateStr1 = "2024-10-11 12:00:00";
                // המרת המחרוזת לאובייקט Timestamp
                Timestamp timestamp1 = Timestamp.valueOf(dateStr1);
                // הדפסת האובייקט Timestamp
                System.out.println(timestamp);


        Timestamp ts = new Timestamp(new Date().getTime());
        Appointment ap = new Appointment("Q3rL9zpUMdTIAAMrBgp7Hp8Ab3r1", timestamp1, true);
        Barber b = new Barber("12340","ddd","dddd","www@eee","12323123213","12323");
        DataBase.BarberNewAppointments(ap);
//        while(clients.isEmpty());
        Log.d("AllClients", String.valueOf(clients.size()));
    }




    public void listener(ArrayList<Client> client)
    {
        this.clients = client;
        Log.d("AllClients", "2 client: " + client.get(1).getFirstName());
    }

}
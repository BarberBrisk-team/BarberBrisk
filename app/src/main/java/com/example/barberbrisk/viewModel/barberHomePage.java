package com.example.barberbrisk.viewModel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;
import com.example.barberbrisk.objects.Appointment;
import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.objects.HairCut;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class barberHomePage extends AppCompatActivity {
    private Barber myObj;
    private Intent myIntent;
    private final FirebaseFirestore db;

    public barberHomePage() {
        db = FirebaseFirestore.getInstance();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_home_page);
        myIntent = getIntent();
//      we need to fill all the object fields by the DB
        String BarberUid = myIntent.getStringExtra("Uid");
        if(BarberUid != null){
            DocumentReference docRef = db.collection("Barbers").document(Objects.requireNonNull(BarberUid));
            docRef.get().addOnSuccessListener(documentSnapshot -> {

                String email = (String) documentSnapshot.get("email");
                String name = (String) documentSnapshot.get("name");
                String password = (String) documentSnapshot.get("password");
                String phone = (String) documentSnapshot.get("phone");
                myObj = new Barber(BarberUid, name, email, phone, password);

                if(documentSnapshot.get("rate") != null)
                    myObj.setRate((Double) documentSnapshot.get("rate"));
                if (documentSnapshot.get("availableAppointments") != null)
                    myObj.setAvailableAppointments((HashMap<String, Appointment>) documentSnapshot.get("availableAppointments"));
                if (documentSnapshot.get("occupiedAppointments") != null)
                    myObj.setOccupiedAppointments((HashMap<String, Appointment>) documentSnapshot.get("occupiedAppointments"));
                if (documentSnapshot.get("haircuts") != null)
                    myObj.setHaircuts((List<HairCut>) documentSnapshot.get("haircuts"));

                int num_of_occupied_appointments = myObj.getOccupiedAppointments().size();
                Appointment[] OccApp = myObj.getOccupiedAppointments().values().toArray(new Appointment[num_of_occupied_appointments]);
                // Initialize the array
                String [] items = new String[num_of_occupied_appointments];

                String oldFormat = "EEE MMM dd HH:mm:ss zzz yyyy";
                for (int i = 0; i < num_of_occupied_appointments; i++) {
                    @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat(oldFormat);
                    items[i] = OccApp[i].getClientName() + "    " + df.format(OccApp[i].getTimeAndDate());
                }
                // Get reference to the ListView
                ListView listView = findViewById(R.id.OccupiedAppointmentsView);

                // Create an ArrayAdapter
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

                // Set the ArrayAdapter as the ListView's adapter
                listView.setAdapter(adapter);
            });
        }
    }
    public void handelButtonProfile(View v){
        myIntent = new Intent(barberHomePage.this, barBerProfilePage.class);
        myIntent.putExtra("Uid", myObj.getUid());
        startActivity(myIntent);
    }

    public void handelButtonAddAppointment(View v){
        myIntent = new Intent(barberHomePage.this, AddAppointmentBarber.class);
        myIntent.putExtra("Uid", myObj.getUid());
        startActivity(myIntent);
    }
}
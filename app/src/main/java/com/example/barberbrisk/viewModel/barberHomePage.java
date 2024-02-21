package com.example.barberbrisk.viewModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;
import com.example.barberbrisk.objects.Appointment;
import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.objects.HairCut;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        Button addHair_btn = findViewById(R.id.addHaircut_btn);

        addHair_btn.setOnClickListener(v -> {
            myIntent = new Intent(barberHomePage.this, AddHaircutPage.class);
            myIntent.putExtra("barber", BarberUid);
            startActivity(myIntent);
        });
        if (BarberUid != null) {
            db.collection("Barbers")
                    .document(BarberUid)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            myObj = documentSnapshot.toObject(Barber.class); // get the barber object from the DB

                            ListView occupiedAppointmentsView = findViewById(R.id.OccupiedAppointmentsView);

                            // Create a list of occupied appointments
                            List<Appointment> occupiedAppointmentsList = new ArrayList<>(myObj.getOccupiedAppointments().values());

                            // Create an ArrayAdapter with a custom getView method
                            ArrayAdapter<Appointment> adapter = new ArrayAdapter<Appointment>(barberHomePage.this, android.R.layout.simple_list_item_2, android.R.id.text1, occupiedAppointmentsList) {
                                @NonNull
                                @Override
                                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                    if (convertView == null) {
                                        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                        convertView = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
                                    }

                                    TextView textViewDate = convertView.findViewById(android.R.id.text1);
                                    TextView textViewClientName = convertView.findViewById(android.R.id.text2);

                                    Appointment appointment = occupiedAppointmentsList.get(position);

                                    // Display the date, time, and client name
                                    textViewDate.setText("Time and Date: " + appointment.getTimeAndDate());
                                    textViewClientName.setText("Client: " + appointment.getClientName());

                                    return convertView;
                                }
                            };

                            // Set the adapter to the ListView
                            occupiedAppointmentsView.setAdapter(adapter);
                        }
                    });
        }
    }


    public void handelButtonProfile(View v) {
        myIntent = new Intent(barberHomePage.this, barBerProfilePage.class);
        myIntent.putExtra("Uid", myObj.getUid());
        startActivity(myIntent);
    }

    public void handelButtonAddAppointment(View v) {
        myIntent = new Intent(barberHomePage.this, AddAppointmentBarber.class);
        myIntent.putExtra("Uid", myObj.getUid());
        startActivity(myIntent);
    }
}
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        TextView barberName = findViewById(R.id.NameTextView);
        TextView DateTextView = findViewById(R.id.DateTextView);

        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = dateFormat.format(new java.util.Date());
        DateTextView.setText(currentDate);

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
                            barberName.setText(myObj.getName());
                            ListView occupiedAppointmentsView = findViewById(R.id.OccupiedAppointmentsView);

                            // Create a list of occupied appointments
                            List<Appointment> occupiedAppointmentsList = new ArrayList<>(myObj.getOccupiedAppointments().values());
                            occupiedAppointmentsList.sort(Comparator.comparing(Appointment::getTimeAndDate));
                            // Create an ArrayAdapter with a custom getView method
                            ArrayAdapter<Appointment> adapter = new ArrayAdapter<Appointment>(barberHomePage.this, android.R.layout.simple_list_item_2, android.R.id.text1, occupiedAppointmentsList) {
                                @SuppressLint("SetTextI18n")
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
                                    @SuppressLint("SimpleDateFormat")
                                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                                    String formattedDate = dateFormat.format(appointment.getTimeAndDate());
                                    textViewDate.setText(formattedDate);
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
        myIntent = new Intent(barberHomePage.this, BarberProfilePage.class);
        myIntent.putExtra("Uid", myObj.getUid());
        startActivity(myIntent);
    }

    public void handelButtonAddAppointment(View v) {
        myIntent = new Intent(barberHomePage.this, AddAppointmentBarber.class);
        myIntent.putExtra("Uid", myObj.getUid());
        startActivity(myIntent);
    }
}
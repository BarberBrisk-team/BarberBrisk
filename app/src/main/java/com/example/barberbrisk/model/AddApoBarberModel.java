package com.example.barberbrisk.model;

import android.util.Log;

import com.example.barberbrisk.DB.DataBase;
import com.example.barberbrisk.objects.Appointment;
import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.objects.Client;
import com.example.barberbrisk.objects.HairCut;
import com.example.barberbrisk.viewModel.AddAppointmentBarber;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AddApoBarberModel {
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public Barber barber;
    private AddAppointmentBarber activity;

    public AddApoBarberModel(AddAppointmentBarber activity) {
        this.activity = activity;

    }

    public void getBarber(String barberUid) {
        db.collection("Barbers")
                .document(barberUid)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        barber = documentSnapshot.toObject(Barber.class);
                    } else {
                        Log.d("BarberSuccess", "Failed");
                    }
                });
    }

        public void addAppointment (Date selectedDateTime){
            Appointment appointment = new Appointment(Timestamp.valueOf(String.valueOf(selectedDateTime)), true);
            barber.addAvailableAppointment(appointment);
            //update the barber in the DB
            DataBase.UpdateBarberDB(barber);

        }
    }

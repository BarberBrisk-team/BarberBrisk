package com.example.barberbrisk.model;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.barberbrisk.DB.DataBase;
import com.example.barberbrisk.objects.Appointment;
import com.example.barberbrisk.objects.Client;
import com.example.barberbrisk.viewModel.ApportionmentOrder;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;

public class AppointmentModel {
    private ApportionmentOrder activity;
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public Client client;


    public AppointmentModel(ApportionmentOrder activity) {
        this.activity = activity;
    }

    public void loadClient(String uid) {
        DocumentReference docRef = db.collection("Clients").document(Objects.requireNonNull(uid));
        docRef.get().addOnSuccessListener(documentSnapshot -> {
            client = documentSnapshot.toObject(Client.class);
        });


    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void UpdateDbAndObjects(Appointment appointment) {
        appointment.setHairCut(activity.selectedHaircutStyle); // set the haircut of the appointment
        client.addAppointment(appointment); // add the appointment to the client
        activity.selectedBarber.removeAvailableAppointment(appointment);
        activity.selectedBarber.addOccupiedAppointment(appointment);
        db.collection("Clients").document(client.getUid()).
                update("appointments", client.getAppointments()); // update the client's appointments in the database
        db.collection("Barbers").document(activity.selectedBarber.getUid()).
                update("availableAppointments", activity.selectedBarber.getAvailableAppointments()); // update the barber's available appointments in the database
        db.collection("Barbers").document(activity.selectedBarber.getUid()).
                update("occupiedAppointments", activity.selectedBarber.getOccupiedAppointments()); // update the barber's occupied appointments in the database
        //update the appointment in the database
        db.collection("Appointments").document(appointment.getAppointmentUid()).set(appointment);
        SetNotificationTimer(appointment);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void SetNotificationTimer(Appointment appointment) {
    RateNotificationManager rateNotificationManager = new RateNotificationManager();
    rateNotificationManager.scheduleNotification(this.activity.getApplicationContext(), activity.selectedBarber.getUid(), client.getUid(), appointment);
    }
}

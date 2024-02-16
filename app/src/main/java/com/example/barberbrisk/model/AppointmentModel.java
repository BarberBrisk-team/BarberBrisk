package com.example.barberbrisk.model;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.barberbrisk.objects.Appointment;
import com.example.barberbrisk.objects.Client;
import com.example.barberbrisk.viewModel.ApportionmentOrder;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class AppointmentModel {
    private ApportionmentOrder activity;
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public Client client;


    public AppointmentModel(ApportionmentOrder activity) {
        this.activity = activity;
    }

    public void loadClient(String uid) {
        DocumentReference docRef = db.collection("Clients").document(uid); // Create a reference to the client's document
        // Create a client object from the docRef
        docRef.get().addOnSuccessListener(documentSnapshot -> { // Get the document from the database
            Log.d("ClientSuccess", "Success"); // Log statement for debugging
            String email = (String) documentSnapshot.get("email"); // Get the email from the document
            String name = (String) documentSnapshot.get("name"); // Get the name from the document
            String password = (String) documentSnapshot.get("password"); // Get the password from the document
            String phone = (String) documentSnapshot.get("phone"); // Get the phone from the document
            HashMap<String, Appointment> appointments =
                    (HashMap<String, Appointment>) documentSnapshot.get("appointments");// Get the appointments from the document as a hashmap if it exists
            client = new Client(uid, name, email, phone, password); // Create a new client object
            //if appointments is not null, set the appointments of the client
            if (appointments != null) { // If the appointments hashmap is not null
                client.setAppointments(appointments); // Set the appointments of the client
            }
        });


    }

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

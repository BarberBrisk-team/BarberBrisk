package com.example.barberbrisk.model;

import static com.example.barberbrisk.DB.DataBase.UpdateBarberDB;

import android.util.Log;

import com.example.barberbrisk.objects.Appointment_combined_version;
import com.example.barberbrisk.objects.Client;
import com.example.barberbrisk.viewModel.ApportionmentOrder;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class AppointmentModel {
    private ApportionmentOrder activity;
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Client client;


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
            HashMap<String, Appointment_combined_version> appointments =
                    (HashMap<String, Appointment_combined_version>) documentSnapshot.get("appointments");// Get the appointments from the document as a hashmap if it exists
            client = new Client(uid, name, email, phone, password); // Create a new client object
            //if appointments is not null, set the appointments of the client
            if (appointments != null) { // If the appointments hashmap is not null
                client.setAppointments(appointments); // Set the appointments of the client
            }
        });


    }

    public void UpdateDB(Appointment_combined_version appointment) {
        appointment.setAvailable(false); // set the appointment to unavailable
        appointment.setHairCut(activity.selectedHaircutStyle); // set the haircut of the appointment
        client.addAppointment(appointment); // add the appointment to the client
        db.collection("Clients").document(client.getUid()).update("appointments", client.getAppointments()); // update the client's appointments in the database
        activity.selectedBarber.removeAvailableAppointment(appointment); // remove the appointment from the barber's available appointments
        activity.selectedBarber.addOccupiedAppointment(appointment); // add the appointment to the barber's occupied appointments
        db.collection("Barbers").document(activity.selectedBarber.getUid()).
                update("availableAppointments", activity.selectedBarber.getAvailableAppointments()); // update the barber's available appointments in the database
        db.collection("Barbers").document(activity.selectedBarber.getUid()).
                update("occupiedAppointments", activity.selectedBarber.getOccupiedAppointments()); // update the barber's occupied appointments in the database

    }

}

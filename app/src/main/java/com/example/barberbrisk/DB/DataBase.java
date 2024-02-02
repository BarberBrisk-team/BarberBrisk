package com.example.barberbrisk.DB;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.barberbrisk.objects.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DataBase {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    /**
     * This method is used to add a new barber to the database.
     * @param FirstName is the first name of the barber.
     * @param LastName is the last name of the barber.
     * @param PhoneNumber is the phone number of the barber.
     * @param ImageFile is a face shot of the barber.
     */
    public static void NewBarber(String FirstName, String LastName, String PhoneNumber, File ImageFile) {

        // Create a new user with a first, middle, and last name
        Map<String, Object> user = new HashMap<>();
        user.put("FirstName", FirstName);
        user.put("LastName", LastName);
        user.put("PhoneNumber", PhoneNumber);
        user.put("Rate", 5);
        //should come here a function that upload image and connect it to the DB
        user.put("ProfileImage", "gs://barberbrisk-c7aad.appspot.com/BarberProfileImages/Barber_0503617555.jpg");


        // Add a new document with a generated ID
        db.collection("Barbers")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("BarberTest", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("BarberTest", "Error adding document", e);
                    }
                });
    }

    /**
     * This method is used to add a new customer to the database.
     * @param FirstName is the first name of the customer.
     * @param LastName is the last name of the customer.
     * @param PhoneNumber is the phone number of the customer.
     */
    public static void NewCustomer(String FirstName, String LastName, String PhoneNumber) {
        // Create a new user with a first name, last name and phone number
        Map<String, Object> user = new HashMap<>();
        user.put("FirstName", FirstName);
        user.put("LastName", LastName);
        user.put("PhoneNumber", PhoneNumber);
        // Add a new document with a generated ID
        db.collection("Clients")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("CustomerTest", "Document Snapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("CustomerTest", "Error occur while adding document to Customers", e);
                    }
                });
    }

    /**
     * This method is used to add new appointments for a barber.
     * @param BarberPhoneNumber is the phone number of the barber.
     * @param date is the date of the appointment.
     * @param time is the time of the appointment.
     */
    public static void BarberNewAppointments(String BarberPhoneNumber, Date date, Time time) {
        //Todo: @elon ezra



    }

    /**
     * This method is used for a customer to arrange an appointment with a barber.
     * @param CustomerPhoneNumber is the phone number of the customer.
     * @param BarberPhoneNumber is the phone number of the barber.
     * @param date is the date of the appointment.
     * @param time is the time of the appointment.
     * @param HairStyleName is the name of the hairstyle the customer wants.
     */
    public static void CustomerArrangeAppointment(String CustomerPhoneNumber, String BarberPhoneNumber, Date date, Time time, String HairStyleName) {
        // Create a new document with a generated ID
        Map<String, Object> appointment = new HashMap<>();
        appointment.put("CustomerPhoneNumber", CustomerPhoneNumber);
        appointment.put("BarberPhoneNumber", BarberPhoneNumber);
        appointment.put("date", date);
        appointment.put("time", time);
        appointment.put("HairStyleName", HairStyleName);

        db.collection("Customer_Appointments")
                .add(appointment)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Customer_Appointments_Test", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Customer_Appointments_Test", "Error adding document", e);
                    }
                });
    }


        /**
         * This method is used to add a new hairstyle to the database.
         * @param HairStyleName is the name of the new hairstyle.
         * @param Price is the price of the new hairstyle.
         * @param ImageFile is an image of the new hairstyle.
         * @param BarberPhoneNumber is the phone number of the barber who can do this hairstyle.
         */
    public static void NewHairStyle(String HairStyleName, double Price, File ImageFile, String BarberPhoneNumber) {
        //Todo: @elon ezra
    }

    /**
     * This method is used for a customer to rate a barber.
     * @param Rating is the rating given by the customer.
     * @param BarberPhoneNumber is the phone number of the barber.
     * @param CustomerPhoneNumber is the phone number of the customer.
     */
    public static void CustomerRating(double Rating, String BarberPhoneNumber, String CustomerPhoneNumber) {
        // Create a new document with a generated ID
        Map<String, Object> appointment = new HashMap<>();
        appointment.put("Rating", Rating);
        appointment.put("BarberPhoneNumber", BarberPhoneNumber);
        appointment.put("CustomerPhoneNumber", CustomerPhoneNumber);

        db.collection("Customer_Rating")
                .add(appointment)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Customer_Rating_Test", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Customer_Rating_Test", "Error adding document", e);
                    }
                });
    }

    /**
     * This method is used to update the rating of a barber.
     * @param NewRating is the new rating of the barber.
     * @param BarberPhone is the phone number of the barber.
     */
    private static void UpdateRating(double NewRating, String BarberPhone) {
        //Todo: @elon ezra
    }

    /**
     * This method is used to get a list of all barbers in the database.
     */
    public static ArrayList<Barber> ListOfBarbers(){
        Log.d("ListOfBarbers", "Hello dear");
        ArrayList<Barber> barbers = new ArrayList<>();
        db.collection("Barbers").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String FirstName = document.getString("FirstName");
                        String LastName = document.getString("LastName");
                        String PhoneNumber = document.getString("PhoneNumber");
//                        Double rate = document.getDouble("Rate");
                        Barber barber = new Barber(FirstName, LastName, "PhoneNumber", 0.0); // replace with actual constructor
                        barbers.add(barber);
                        Log.d("ListOfBarbers", barber.toString(), task.getException());
                    }
                } else {
                    Log.d("ListOfBarbers-Test-Fail", "Error getting documents: ", task.getException());
                }
            }
        });
        return barbers;
    }

    public interface FirestoreCallback {
        void onCallback(ArrayList<User> clients);
    }

    /**
     * This method is used to get a list of all customers in the database.
     */
//    public static void ListOfCustomer(){
//        //Todo: @elon ezra
//
//
//    }
    public static void ListOfCustomer(OnSuccessListener<QuerySnapshot> os) {
        db.collection("Clients").get().addOnSuccessListener(os);
    }
    public interface OnDataFetchedListener {
        void onDataFetched(ArrayList<Client> clients);
    }


    public static void fetchClients(OnDataFetchedListener listener) {
        ArrayList<Client> clients = new ArrayList<>();

        db.collection("Clients")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String FirstName = document.getString("FirstName");
                                String LastName = document.getString("LastName");
                                String PhoneNumber = document.getString("PhoneNumber");
                                String uid = document.getId();
                                clients.add(new Client(uid, FirstName, LastName,"", PhoneNumber));
                            }
                            listener.onDataFetched(clients);
                        } else {
                            Log.d("ListOfCustomer", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


}

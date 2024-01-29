package com.example.barberbrisk;

import android.os.Debug;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DataBase {

    /**
     * This method is used to add a new barber to the database.
     * @param FirstName is the first name of the barber.
     * @param LastName is the last name of the barber.
     * @param PhoneNumber is the phone number of the barber.
     * @param ImageFile is a face shot of the barber.
     */
    public static void NewBarber(String FirstName, String LastName, String PhoneNumber, File ImageFile) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Create a new user with a first, middle, and last name
        Map<String, Object> user = new HashMap<>();
        user.put("FirstName", FirstName);
        user.put("LastName", LastName);
        user.put("PhoneNumber", PhoneNumber);
        user.put("Rate", 5);
        //should come here a function that upload image and connect it to the DB
        user.put("ProfileImage", "http://example.com");


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
    public static void NewCostumer(String FirstName, String LastName, int PhoneNumber) {

    }

    /**
     * This method is used to add new appointments for a barber.
     * @param BarberPhoneNumber is the phone number of the barber.
     * @param date is the date of the appointment.
     * @param time is the time of the appointment.
     */
    public static void BarberNewAppointments(int BarberPhoneNumber, Date date, Time time) {
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
    public static void CustomerArrangeAppointment(int CustomerPhoneNumber, int BarberPhoneNumber, Date date, Time time, String HairStyleName) {

    }

    /**
     * This method is used to add a new hairstyle to the database.
     * @param HairStyleName is the name of the new hairstyle.
     * @param Price is the price of the new hairstyle.
     * @param ImageFile is an image of the new hairstyle.
     * @param BarberPhoneNumber is the phone number of the barber who can do this hairstyle.
     */
    public static void NewHairStyle(String HairStyleName, double Price, File ImageFile, int BarberPhoneNumber) {
        //Todo: @elon ezra
    }

    /**
     * This method is used for a customer to rate a barber.
     * @param Rating is the rating given by the customer.
     * @param BarberPhone is the phone number of the barber.
     * @param CostumerPhone is the phone number of the customer.
     */
    public static void CostumerRating(double Rating, int BarberPhone, int CostumerPhone) {

    }

    /**
     * This method is used to update the rating of a barber.
     * @param NewRating is the new rating of the barber.
     * @param BarberPhone is the phone number of the barber.
     */
    private static void UpdateRating(double NewRating, int BarberPhone) {
        //Todo: @elon ezra
    }

    /**
     * This method is used to get a list of all barbers in the database.
     */
    public static <Barber> void ListOfBarbers(){

    }

    /**
     * This method is used to get a list of all customers in the database.
     */
    public static <Costumer> void ListOfCostumer(){
        //Todo: @elon ezra

        
    }
}

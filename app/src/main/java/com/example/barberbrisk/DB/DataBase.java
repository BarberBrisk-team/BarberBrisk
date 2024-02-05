package com.example.barberbrisk.DB;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.barberbrisk.objects.Appointment;
import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.objects.Client;
import java.sql.Timestamp;

import com.example.barberbrisk.objects.ClientAppointment;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DataBase {
    Map<String ,Appointment> AppointmentList  = new HashMap<>();


    @SuppressLint("StaticFieldLeak")
    static FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static void NewBarberDB(Barber barber) {
        db.collection("Barbers").document(barber.getUid()).set(barber);
    }
    public static void NewClientDB(Client client){
        db.collection("Client").document(client.getUid()).set(client);
    }

    public Map<String, Appointment> getAppointmentList() {
        return AppointmentList;
    }

    public void setAppointmentList(Map<String, Appointment> appointmentList) {
        AppointmentList = appointmentList;
    }
    /**
     * This method is used to generate a number of appointments for a barber.
     * @param BarberID is the ID of the barber for whom the appointments are to be generated.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void GenerateBarberAppointments(String BarberID) {

        int duration_date = 3;
        Timestamp current = new Timestamp(new Date().getTime());

        for (int i = 0; i < duration_date; i++)
        {
            Appointment appointment = new Appointment(BarberID, current, true);
            BarberNewAppointment(appointment);
            current.setTime(current.getTime() + 86400000);
            Log.d("BarberNewAppointments","BarberNewAppointments");
        }

    }



    /**
     * This method is used to add a new appointment to the database.
     * @param appointment is the appointment to be added to the database.
     */
    public static void BarberNewAppointment(Appointment appointment) {
        db.collection("Apointments").document().set(appointment);
        Log.d("BarberNewAppointments","BarberNewAppointments");
    }

    public static void SetClientAppointment(ClientAppointment clientAppointment)
    {
        db.collection("ClientAppointment").document().set(clientAppointment);
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
                .addOnSuccessListener(documentReference -> Log.d("Customer_Rating_Test", "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w("Customer_Rating_Test", "Error adding document", e));
    }

    /**
     * This method is used to update the rating of a barber.
     * @param NewRating is the new rating of the barber.
     * @param BarberPhone is the phone number of the barber.
     */
    private static void UpdateRating(double NewRating, String BarberPhone) {  // to prove the mean problem
        //Todo: @elon ezra
    }

    /**
     * This interface is used as a callback mechanism to handle the asynchronous retrieval of data from the database.
     * It provides a method that will be invoked when the data (in this case, a list of Barber objects) is successfully fetched.
     */
    public interface OnDataFetchedListenerBarbers{

        /**
         * This method is called when the data fetching process is completed.
         * It receives an ArrayList of Barber objects as a parameter, which represents the data fetched from the database.
         *
         * @param barbers An ArrayList of Barber objects representing the data fetched from the database.
         */
        void onDataFetchedBarbers(ArrayList<Barber> barbers);
    }


    /**
     * This method is used to asynchronously fetch a list of all barbers from the database.
     * It uses the Firestore API to get the data and adds each barber to an ArrayList.
     * Once all the data has been fetched, it triggers a callback with the list of barbers.
     *
     * @param callback An instance of OnDataFetchedListenerBarbers. Its onDataFetchedBarbers method will be called with the list of barbers when the data has been completely fetched.
     */
    public static void ListOfBarbers(OnDataFetchedListenerBarbers callback){
        Log.d("Running", "Hello dear");
        ArrayList<Barber> barbers = new ArrayList<>();
        db.collection("Barbers").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Barber barber = document.toObject(Barber.class);
                    barbers.add(barber);
                }
                callback.onDataFetchedBarbers(barbers); // Trigger the callback
            } else {
                Log.d("ListOfBarbers-Test-Fail", "Error getting documents: ", task.getException());
            }
        });
    }


    /**
     * This method is used to get a list of all customers in the database.
     */
    public interface OnDataFetchedListenerClients {
        void onDataFetchedClients(ArrayList<Client> clients);
    }

    public static void ListOfClient(OnDataFetchedListenerClients listener) {
        ArrayList<Client> clients = new ArrayList<>();

        //db.collection("Clients").addSnapshotListener();
    }

}


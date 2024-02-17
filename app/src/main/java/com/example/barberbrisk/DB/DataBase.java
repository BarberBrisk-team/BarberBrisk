package com.example.barberbrisk.DB;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.barberbrisk.objects.Appointment;
import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.objects.Client;
import com.example.barberbrisk.objects.ClientAppointment;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DataBase {
    static Map<String , Appointment> AppointmentList  = new HashMap<>();

    static Map<String ,Barber> Baraberlist  = new HashMap<>();

    public static Map<String, Barber> getBaraberlist() {
//        if(Baraberlist.isEmpty())
//        {
////            throw new Exception("The list is empty. Call the method DownladBarberList() first.");
//        }
        return Baraberlist;
    }



    @SuppressLint("StaticFieldLeak")
    static FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static void NewBarberDB(Barber barber) {
        UpdateBarberDB(barber);
    }
    public static void NewClientDB(Client client){
        UpdateClientDB(client);
    }

    public static void UpdateBarberDB(Barber barber) {
        db.collection("Barbers").document(barber.getUid()).set(barber);
    }

    public static void UpdateClientDB(Client client) {
        db.collection("Clients").document(client.getUid()).set(client);
    }
   public static void DownloadBarberList(){
        db.collection("Barbers").get().addOnCompleteListener(
                task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        QuerySnapshot doc = task.getResult();
                        for (DocumentSnapshot documentSnapshot: doc.getDocuments()) {
                            Baraberlist.put(documentSnapshot.getId(),documentSnapshot.toObject(Barber.class));
                        }
                        Log.d("DownloadListAppoinment", Baraberlist.toString());
                    } else
                        Log.d(TAG, "Error getting documents: ", task.getException());
                }
        );
   }

    public static Map<String, Appointment> getAppointmentList() {
        return AppointmentList;
    }

    public void setAppointmentList(Map<String, Appointment> appointmentList) {
        AppointmentList = appointmentList;
    }


    public static void DownloadListAppoinment()
    {
        db.collection("Apointments").get().addOnCompleteListener(
                task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        QuerySnapshot doc = task.getResult();
                        for (DocumentSnapshot documentSnapshot: doc.getDocuments()) {
                            AppointmentList.put(documentSnapshot.getId(),documentSnapshot.toObject(Appointment.class));
                        }
                        Log.d("DownloadListAppoinment", "download "+AppointmentList.size() + " appointments");
                    } else
                        Log.d(TAG, "Error getting documents: ", task.getException());
                }
        );
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
        /*

         */

        public static void UpdateBarberAppointments(String appointmentID, Appointment appointment) {
            db.collection("Apointments").document(appointmentID).set(appointment);
        }
    public static void NewHairStyle(String HairStyleName, double Price, File ImageFile, String BarberPhoneNumber) {
        //Todo: @elon ezra
    }

    /**
     * This method is used for a customer to rate a barber.
     * @param Rating is the rating given by the customer.
     * @param BarberID is the phone number of the barber.
     * @param ClientID is the phone number of the customer.
     */
    public static void setCustomerRatingDB(double Rating, String BarberID, String ClientID) {
        // Create a new document with a generated ID
        Map<String, Object> appointment = new HashMap<>();
        appointment.put("Rating", Rating);
        appointment.put("BarberID", BarberID);
        appointment.put("ClientID", ClientID);

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


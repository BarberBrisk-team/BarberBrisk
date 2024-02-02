package com.example.barberbrisk.DB;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.objects.Client;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DataBase {
    @SuppressLint("StaticFieldLeak")
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
                .addOnSuccessListener(documentReference -> Log.d("BarberTest", "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w("BarberTest", "Error adding document", e));
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
                .addOnSuccessListener(documentReference -> Log.d("CustomerTest", "Document Snapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w("CustomerTest", "Error occur while adding document to Customers", e));
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
                .addOnSuccessListener(documentReference -> Log.d("Customer_Appointments_Test", "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w("Customer_Appointments_Test", "Error adding document", e));
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
    private static void UpdateRating(double NewRating, String BarberPhone) {
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
                    String FirstName = document.getString("FirstName");
                    String LastName = document.getString("LastName");
                    String PhoneNumber = document.getString("PhoneNumber");
//                        Double rate = document.getDouble("Rate");
                    Barber barber = new Barber(FirstName, LastName, "PhoneNumber", 0.0);
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

    public static void ListOfCustomer(OnDataFetchedListenerClients listener) {
        ArrayList<Client> clients = new ArrayList<>();

        db.collection("Clients")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String FirstName = document.getString("FirstName");
                            String LastName = document.getString("LastName");
                            String PhoneNumber = document.getString("PhoneNumber");
                            String uid = document.getId();
                            clients.add(new Client(uid, FirstName, LastName,"", PhoneNumber));
                        }
                        listener.onDataFetchedClients(clients);
                    } else {
                        Log.d("ListOfCustomer", "Error getting documents: ", task.getException());
                    }
                });
    }



//    public static void fetch_a_Client(String uid,OnDataFetchedListener listener)
//    {
//        db.collection("Clients").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if(task.isSuccessful())
//                {
//                    for (:
//                         ) {
//
//                    }
//                }
//            }
//        });
//
//    }


}


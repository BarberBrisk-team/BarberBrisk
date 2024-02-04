package com.example.barberbrisk.DB;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.barberbrisk.objects.Appointment;
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
     */
    public static void NewBarberDB(String Uid, String FirstName, String LastName, String PhoneNumber, String Email) {

        // Create a new user with a first, middle, and last name
//        Map<String, Object> user = new HashMap<>();
//        user.put("FirstName", FirstName);
//        user.put("LastName", LastName);
//        user.put("PhoneNumber", PhoneNumber);
//        user.put("Rate", 5);
//        //should come here a function that upload image and connect it to the DB
//        user.put("ProfileImage", "gs://barberbrisk-c7aad.appspot.com/BarberProfileImages/Barber_0503617555.jpg");
//<<<<<<< HEAD
////        Barber barber = new Barber(Uid, FirstName, LastName, PhoneNumber, Email,"");
////        db.collection("Barbers").document(Uid).set(barber);
////        // Add a new document with a generated ID
//=======
////        Barber barber = new Barber(Uid, FirstName, LastName, PhoneNumber, Email," ");
//        db.collection("Barbers").document(Uid).set(barber);
        // Add a new document with a generated ID

//        db.collection("Barbers")
//                .add(user)
//                .addOnSuccessListener(documentReference -> Log.d("BarberTest", "DocumentSnapshot added with ID: " + documentReference.getId()))
//                .addOnFailureListener(e -> Log.w("BarberTest", "Error adding document", e));
    }
    public static void NewBarberDB(Barber barber) {
        db.collection("Barbers").document(barber.getUid()).set(barber);
    }
    /**
     * This method is used to add a new customer to the database.
     * @param FirstName is the first name of the customer.
     * @param LastName is the last name of the customer.
     * @param PhoneNumber is the phone number of the customer.
     */
    public static void NewClientDB(String FirstName, String LastName, String PhoneNumber) {
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
    public static void NewClientDB(){return;} // to fill with an object

    public static void GenerateBarberAppointments(String BarberID) {

        int duration_date = 0;
        Timestamp current = Timestamp.now();
        for (int i = 0; i < duration_date; i++)
        {
//            Appointment appointment = new Appointment(BarberID, current,true);
//            db.collection("Apointments").document().set(appointment);
//            current = current + 86400000;
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
    public static void CustomerArrangeAppointment(){} // fill with object of customer, appoit... Barber

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
//                    String FirstName = barber.getFirstName();
//                    String LastName = barber.getLastName();
                    String PhoneNumber = barber.getPhone();
                    Double rate = barber.getRate();
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


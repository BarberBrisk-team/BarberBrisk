package com.example.barberbrisk.viewModel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;
import com.example.barberbrisk.objects.Client;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class clientProfilePage extends AppCompatActivity {
    private Client myClient;
    private final FirebaseFirestore db;

    public clientProfilePage() {
        db = FirebaseFirestore.getInstance();
    }

    /**
     * This function is called when the activity is created.
     * It gets the client details from the DB and sets the data on the activity.
     * @param savedInstanceState - the saved instance state
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile_page);


//      write a message on the screen that the activity start
        Log.d("test", "start profile activity");

//      create a obj of the first activity and take data about the user.
        Intent clientIntent = getIntent();
        String ClientUid = clientIntent.getStringExtra("Uid");

        // get the client details from the DB
        DocumentReference docRef = db.collection("Clients").document(Objects.requireNonNull(ClientUid));
        docRef.get().addOnSuccessListener(documentSnapshot -> {
            Log.d("ClientSuccess", "Success");
            String email = (String) documentSnapshot.get("email");
            String name = (String) documentSnapshot.get("name");
            String password = (String) documentSnapshot.get("password");
            String phone = (String) documentSnapshot.get("phone");
            Log.d("Client details", "name: " + name + "email: " + email + " ");
            myClient = new Client(ClientUid, name, email, phone, password);
            Log.d("ClientSuccess", "Success2" + myClient.getName());

            // checking of the list of appointments of the client is empty if so, we initialize the
            // list to be "empty"
//            HashMap<String, Appointment> appointmentHashMap = (HashMap<String, Appointment>) documentSnapshot.get("appointments");
//            String appointments = "";
//            if (appointmentHashMap == null)
//                appointments = "empty";
//            else
//                appointments = appointmentHashMap.toString();

            //  set data on the activity
            TextView b1 = findViewById(R.id.buttonName);
            TextView b2 = findViewById(R.id.buttonEmail);
            TextView b3 = findViewById(R.id.buttonPhone);
//            Button b4 = findViewById(R.id.buttonAppointHist);
            b1.setText(myClient.getName());
            b2.setText(myClient.getEmail());
            b3.setText(myClient.getPhone());
//            b4.setText("My Appointments");
        });
    }
    /**
     * This function is called when the user clicks on the "back" button.
     * It returns the user to the previous activity.
     * @param v - the view
     */
    public void arrowBackButton(View v) {
        Intent i = new Intent(this, clientHomePage.class);
        startActivity(i);
    }

    public void handelButtonAppointments(View v) {
        Intent i = new Intent(this, clientAppointmentsPage.class);
        i.putExtra("Uid", myClient.getUid());
        startActivity(i);
    }
}
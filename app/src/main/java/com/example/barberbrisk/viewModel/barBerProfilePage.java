package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;
import com.example.barberbrisk.objects.Appointment;
import com.example.barberbrisk.objects.Barber;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;

public class barBerProfilePage extends AppCompatActivity {
    private Barber barber;
    private final FirebaseFirestore db;
    public barBerProfilePage() {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_ber_profile_page);

        //write a message on the screen that the activity start
        Log.d("barberProfile", "start Barber profile activity");

        //create a obj of the first activity and take data about the user.
        Intent barberIntent = getIntent();
        String BarberUid = barberIntent.getStringExtra("Uid");

        DocumentReference docRef = db.collection("Barbers").document(Objects.requireNonNull(BarberUid));
        docRef.get().addOnSuccessListener(documentSnapshot -> {
            String email = (String) documentSnapshot.get("email");
            String name = (String) documentSnapshot.get("name");
            String password = (String) documentSnapshot.get("password");
            String phone = (String) documentSnapshot.get("phone");
            barber = new Barber(BarberUid, name, email, phone, password);

            if(documentSnapshot.get("rate") != null)
                barber.setRate((Double) documentSnapshot.get("rate"));
            if (documentSnapshot.get("availableAppointments") != null)
                barber.setAvailableAppointments((HashMap<String, Appointment>) documentSnapshot.get("availableAppointments"));
            if (documentSnapshot.get("occupiedAppointments") != null)
                barber.setOccupiedAppointments((HashMap<String, Appointment>) documentSnapshot.get("occupiedAppointments"));
            
            //set data on the activity
            Button b1 = findViewById(R.id.buttonNameBarber);
            Button b2 = findViewById(R.id.buttonEmailBarber);
            Button b3 = findViewById(R.id.buttonPhoneBarber);
//            Button b4 = findViewById(R.id.buttonRatingBarber);
//            Button b5 = findViewById(R.id.available_appointments);
//            Button b6 = findViewById(R.id.occupied_appointments);
            b1.setText(barber.getName());
            b2.setText(barber.getEmail());
            b3.setText(barber.getPhone());
//            b4.setText(barber.getPhone());
//            b5.setText(barber.getAvailableAppointments().toString());
//            b6.setText(barber.getOccupiedAppointments());

        });
    }
    public void arrowBackButton(View v){
        Intent i = new Intent(barBerProfilePage.this, barberHomePage.class);
        startActivity(i);
    }

}
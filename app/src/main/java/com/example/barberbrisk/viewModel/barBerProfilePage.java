package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;
import com.example.barberbrisk.objects.Appointment;
import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.objects.HairCut;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
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
        setContentView(R.layout.activity_barber_profile_page);

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
                barber.setRating((Double) documentSnapshot.get("rate"));
            if (documentSnapshot.get("availableAppointments") != null)
                barber.setAvailableAppointments((HashMap<String, Appointment>) documentSnapshot.get("availableAppointments"));
            if (documentSnapshot.get("occupiedAppointments") != null)
                barber.setOccupiedAppointments((HashMap<String, Appointment>) documentSnapshot.get("occupiedAppointments"));
            if (documentSnapshot.get("haircuts") != null)
                barber.setHaircuts((List<HairCut>) documentSnapshot.get("haircuts"));

            //set data on the activity
            TextView b1 = findViewById(R.id.buttonNameBarber);
            TextView b2 = findViewById(R.id.buttonEmailBarber);
            TextView b3 = findViewById(R.id.buttonPhoneBarber);
            TextView b4 = findViewById(R.id.buttonHairCutsBarber);
            TextView b5 = findViewById(R.id.buttonBarberRating);
            TextView b6 = findViewById(R.id.buttonAvailableAppointments);
            TextView b7 = findViewById(R.id.buttonOccupiedAppointments);
            b1.setText(barber.getName());
            b2.setText(barber.getEmail());
            b3.setText(barber.getPhone());
            b4.setText(barber.getPhone());
            b5.setText(barber.getAvailableAppointments().toString());
            b6.setText(barber.getOccupiedAppointments().toString());
            b7.setText(barber.getHaircuts().toString());

        });
    }
    public void arrowBackButton(View v){
        Intent i = new Intent(barBerProfilePage.this, barberHomePage.class);
        startActivity(i);
    }

}
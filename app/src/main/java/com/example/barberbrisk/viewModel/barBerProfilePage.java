package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;
import com.example.barberbrisk.objects.Barber;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

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
        barber = barberIntent.getParcelableExtra("BarberObject");
        DocumentReference docRef = db.collection("Barbers").document(Objects.requireNonNull(barber.getUid()));
        docRef.get().addOnSuccessListener(documentSnapshot -> {
            //set data on the activity
            Button b1 = findViewById(R.id.buttonNameBarber);
            Button b2 = findViewById(R.id.buttonEmailBarber);
            Button b3 = findViewById(R.id.buttonPhoneBarber);
            b1.setText(barber.getName());
            b2.setText(barber.getEmail());
            b3.setText(barber.getPhone());
        });
    }
    public void arrowBackButton(View v){
        Intent i = new Intent(barBerProfilePage.this, barberHomePage.class);
        startActivity(i);
    }
}
package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;
import com.example.barberbrisk.objects.Appointment;
import com.example.barberbrisk.objects.Client;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;

public class clientAppointmentsPage extends AppCompatActivity {
    private Client myObj;
    private final FirebaseFirestore db;

    public clientAppointmentsPage() {
        db = FirebaseFirestore.getInstance();
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_appointments_page);
        Intent myIntent = getIntent();
        String ClientUid = myIntent.getStringExtra("Uid");

        DocumentReference docRef = db.collection("Clients").document(Objects.requireNonNull(ClientUid));
        docRef.get().addOnSuccessListener(documentSnapshot -> {
            HashMap<String, Appointment> appointmentHashMap = (HashMap<String, Appointment>) documentSnapshot.get("appointments");
            String appointments = "";
            if (appointmentHashMap == null)
                appointments = "empty";
            else
                appointments = appointmentHashMap.toString();

            TextView t = findViewById(R.id.textView5);
            t.setText(appointments);
        });

    }
}

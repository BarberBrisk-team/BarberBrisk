package com.example.barberbrisk.viewModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;
import com.example.barberbrisk.objects.Appointment;
import com.example.barberbrisk.objects.Client;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

        if (ClientUid != null){
            DocumentReference docRef = db.collection("Clients").document(Objects.requireNonNull(ClientUid));
            docRef.get().addOnSuccessListener(documentSnapshot -> {
                myObj = documentSnapshot.toObject(Client.class);
                ListView AppointmentsView = findViewById(R.id.AppointmentsView);
                Log.d("ClientSuccess", "SuccessAppointmentView" + AppointmentsView);
//                Map<String, Appointment> appointmentHashMap = (HashMap<String, Appointment>) documentSnapshot.get("appointments");
                Log.d("ClientSuccess", "Success2" + myObj.getAppointments());
                // Create a list of occupied appointments
                List<Appointment> AppointmentsList = new ArrayList<>(myObj.getAppointments().values());
                Log.d("ClientSuccess", "SuccessList" + AppointmentsList);
                AppointmentsList.sort(Comparator.comparing(Appointment::getTimeAndDate));
                // Create an ArrayAdapter with a custom getView method
                ArrayAdapter<Appointment> adapter = new ArrayAdapter<Appointment>(clientAppointmentsPage.this, android.R.layout.simple_list_item_2, android.R.id.text1, AppointmentsList) {
                    @SuppressLint("SetTextI18n")
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        Log.d("ClientSuccess", "SuccessViewFunction" + convertView);
                        if (convertView == null) {
                            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            convertView = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
                        }
                        Log.d("ClientSuccess", "SuccessConvertView" + convertView);
                        TextView textViewDate = convertView.findViewById(android.R.id.text1);
                        TextView textViewClientName = convertView.findViewById(android.R.id.text2);

                        Appointment appointment = AppointmentsList.get(position);

                        // Display the date, time, and client name
                        @SuppressLint("SimpleDateFormat")
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        String formattedDate = dateFormat.format(appointment.getTimeAndDate());
                        textViewDate.setText(formattedDate);
                        textViewClientName.setText("Barber: " + appointment.getBarberName());

                        return convertView;
                    }
                };

                // Set the adapter to the ListView
                AppointmentsView.setAdapter(adapter);
            });

        }
    }
}

package com.example.barberbrisk.viewModel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;

import com.example.barberbrisk.objects.Appointment_combined_version;
import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.objects.HairCut;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class appionment_oredr extends AppCompatActivity {
    private Spinner barbersSpinner;
    private Spinner appointmentsSpinner;
    private Button selectBarberButton;
    private Button selectAppointmentButton;
    private Button haircutButton;

    private Barber selectedBarber;
    private Appointment_combined_version selectedAppointment;
    private HairCut selectedHaircutStyle;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appionment_oredr);

        barbersSpinner = findViewById(R.id.barbersSpinner);
        appointmentsSpinner = findViewById(R.id.appointmentsSpinner);
        selectBarberButton = findViewById(R.id.barbersButton);
        selectAppointmentButton = findViewById(R.id.button6);
        haircutButton = findViewById(R.id.haircutButton);

        // Hide the spinners initially
        barbersSpinner.setVisibility(View.GONE);
        appointmentsSpinner.setVisibility(View.GONE);

        selectBarberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the spinners when the button is pressed
                barbersSpinner.setVisibility(View.VISIBLE);
                appointmentsSpinner.setVisibility(View.VISIBLE);
                loadBarbersIntoSpinner();
            }
        });

        selectAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedBarber != null) {
                    selectedAppointment = (Appointment_combined_version) appointmentsSpinner.getSelectedItem();
                }
            }
        });

        haircutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedBarber != null) {
                    // Show a dialog or navigate to another activity to choose a haircut style
                    // For simplicity, I'll use the first haircut style
                    selectedHaircutStyle = selectedBarber.getHaircuts().get(0);
                }
            }
        });
    }

    private void loadBarbersIntoSpinner() {
        // Fetch barbers from Firebase
        db.collection("Barbers")
                .get()
                .addOnSuccessListener(result -> {
                    List<Barber> barberList = result.toObjects(Barber.class);
                    //print to log the barbers and their appointments for testing

                    ArrayAdapter<Barber> adapter = new ArrayAdapter<Barber>(this, android.R.layout.simple_spinner_item, barberList) {
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);
                            // Show only the name in the selected item
                            ((TextView) view.findViewById(android.R.id.text1)).setText(barberList.get(position).getName());
                            return view;
                        }

                        @Override
                        public View getDropDownView(int position, View convertView, ViewGroup parent) {
                            View view = super.getDropDownView(position, convertView, parent);
                            // Show both name and rate in the dropdown items
                            ((TextView) view.findViewById(android.R.id.text1)).setText(barberList.get(position).getName() + " - Rate: " + barberList.get(position).getRate());
                            return view;
                        }
                    };

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    barbersSpinner.setAdapter(adapter);

                    // Set a listener for when a barber is selected
                    barbersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            // Load appointments for the selected barber
                            selectedBarber = (Barber) barbersSpinner.getSelectedItem();
                            loadAppointmentsIntoSpinner();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            // Handle when nothing is selected
                        }
                    });
                })
                .addOnFailureListener(exception -> {
                    // Handle failures
                    Log.e("FirebaseError", "Error fetching barbers from Firebase", exception);
                });
    }

    private void loadAppointmentsIntoSpinner() {
        if (selectedBarber != null) {
            db.collection("Barbers")
                    .document(selectedBarber.getUid())
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            Barber updatedBarber = documentSnapshot.toObject(Barber.class);

                            // Log the updated AvailableAppointments field
                            if (updatedBarber != null && updatedBarber.getAvailableAppointments() != null) {
                                for (Map.Entry<String, Appointment_combined_version> entry : updatedBarber.getAvailableAppointments().entrySet()) {
                                    String appointmentID = entry.getKey();
                                    Appointment_combined_version appointment = entry.getValue();
                                    Log.d("UpdatedAppointmentData", "Appointment ID: " + appointmentID + ", Data: " );
                                }
                            }
                        }
                    })
                    .addOnFailureListener(exception -> {
                        // Handle failures
                        Log.e("FirebaseError", "Error fetching updated barber data from Firebase", exception);
                    });
        }
    }
}


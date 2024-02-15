package com.example.barberbrisk.viewModel;

import static com.example.barberbrisk.DB.DataBase.UpdateBarberDB;
import static com.example.barberbrisk.DB.DataBase.UpdateClientDB;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;
import com.example.barberbrisk.model.AppointmentModel;
import com.example.barberbrisk.objects.Appointment_combined_version;
import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.objects.Client;
import com.example.barberbrisk.objects.HairCut;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ApportionmentOrder extends AppCompatActivity {
    private Spinner barbersSpinner;
    private Spinner appointmentsSpinner;
    private Spinner haircutsSpinner;  // Add a spinner for haircuts
    private Button selectBarberButton;
    private Button selectAppointmentButton;
    private Button haircutButton;

    public Barber selectedBarber;
    private Appointment_combined_version selectedAppointment;
    public HairCut selectedHaircutStyle;
    private AppointmentModel model;

    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appionment_oredr);
        this.model = new AppointmentModel(this);
        // Initialize the spinners and buttons
        barbersSpinner = findViewById(R.id.barbersSpinner);
        appointmentsSpinner = findViewById(R.id.appointmentsSpinner);
        haircutsSpinner = findViewById(R.id.haircutsSpinner);  // Initialize the haircuts spinner
        selectBarberButton = findViewById(R.id.barbersButton);
        selectAppointmentButton = findViewById(R.id.button6);
        haircutButton = findViewById(R.id.haircutButton);
        // Hide the spinners initially
        barbersSpinner.setVisibility(View.GONE);
        appointmentsSpinner.setVisibility(View.GONE);
        haircutsSpinner.setVisibility(View.GONE);
        //get the uid that was pass from the last activity and use it to get the client object
        Intent myIntent = getIntent();
        String ClientUid = myIntent.getStringExtra("Uid");
        if (ClientUid != null) {
            model.loadClient(ClientUid); // Load the client object from Firebase
        } else {
            Log.e("ClientUid", "ClientUid is null");
        }


        // Initialize the spinners and buttons


        selectBarberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barbersSpinner.setVisibility(View.VISIBLE);
                loadBarbersIntoSpinner();
            }
        });

        selectAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedBarber != null) {
                    appointmentsSpinner.setVisibility(View.VISIBLE);
                    selectedAppointment = (Appointment_combined_version) appointmentsSpinner.getSelectedItem();
                }
            }
        });

        haircutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedBarber != null) {
                    // Get the selected haircut from the adapter instead of directly from the spinner
                    haircutsSpinner.setVisibility(View.VISIBLE); // Show the haircuts spinner
                    int selectedHaircutPosition = haircutsSpinner.getSelectedItemPosition(); // Get the position of the selected haircut
                    if (selectedHaircutPosition != AdapterView.INVALID_POSITION) { // Check if a haircut is selected
                        selectedHaircutStyle = (HairCut) haircutsSpinner.getAdapter().getItem(selectedHaircutPosition);
                    }
                }
            }
        });

        //handle the submit button
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(view -> {
            if (selectedBarber != null && selectedAppointment != null && selectedHaircutStyle != null) {
                // Create an appointment object and save it to the client's appointments
//                    Appointment_combined_version appointment = selectedAppointment;
//                    appointment.setHairCut(selectedHaircutStyle);
//                    appointment.setAvailable(false);
//                    client.addAppointment(appointment);
//                    selectedBarber.removeAvailableAppointment(appointment);
//                    selectedBarber.addOccupiedAppointment(appointment);
//                    // Save the updated client object to Firebase
//                    UpdateClientDB(client);
//                    UpdateBarberDB(selectedBarber);
//                    // Navigate to the client's home page
                model.UpdateDB(selectedAppointment);
                goBackHome(view);
            }
        });
    }

    private void loadBarbersIntoSpinner() {
        // Fetch barbers from Firebase
        model.db.collection("Barbers")
                .get()
                .addOnSuccessListener(result -> {
                    List<Barber> barberList = result.toObjects(Barber.class);

                    ArrayAdapter<Barber> adapter = new ArrayAdapter<Barber>(this, android.R.layout.simple_spinner_item, barberList) {
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);
                            // Show only the name in the selected item
                            ((TextView) view.findViewById(android.R.id.text1)).setText(barberList.get(position).getName());
                            return view;
                        }

                        @SuppressLint("SetTextI18n")
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
                            // Load appointments and haircuts for the selected barber
                            selectedBarber = (Barber) barbersSpinner.getSelectedItem();
                            loadAppointmentsIntoSpinner();
                            loadHaircutsIntoSpinner();
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
            Map<String, Appointment_combined_version> availableAppointments = selectedBarber.getAvailableAppointments();

            if (availableAppointments != null && !availableAppointments.isEmpty()) {
                List<Appointment_combined_version> appointments = new ArrayList<>(availableAppointments.values());

                ArrayAdapter<Appointment_combined_version> adapter = new ArrayAdapter<Appointment_combined_version>(this, android.R.layout.simple_spinner_item, appointments) {
                    @NonNull
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        // Show date and time in the selected item
                        String formattedDateTime = formatDateTime(appointments.get(position).getTimeAndDate());
                        ((TextView) view.findViewById(android.R.id.text1)).setText(formattedDateTime);
                        return view;
                    }

                    @Override
                    public View getDropDownView(int position, View convertView, ViewGroup parent) {
                        View view = super.getDropDownView(position, convertView, parent);
                        // Show date and time in the dropdown items
                        String formattedDateTime = formatDateTime(appointments.get(position).getTimeAndDate());
                        ((TextView) view.findViewById(android.R.id.text1)).setText(formattedDateTime);
                        return view;
                    }
                };

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                appointmentsSpinner.setAdapter(adapter);

                // Set a listener for when an appointment is selected
                appointmentsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                        // Handle when an appointment is selected
                        selectedAppointment = (Appointment_combined_version) appointmentsSpinner.getSelectedItem();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        // Handle when nothing is selected
                        Log.e("NoAppointmentSelected", "No appointment selected.");
                    }
                });
            } else {
                // Handle the case where there are no appointments for the selected barber
                Toast.makeText(this, "No appointments found for the selected barber.", Toast.LENGTH_SHORT).show();
                Log.e("NoAppointments", "No appointments found for the selected barber.");
            }
        }
    }

    private String formatDateTime(Date date) {
        // Format the date and time as needed (e.g., using SimpleDateFormat)
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(date);
    }

    private void loadHaircutsIntoSpinner() {
        if (selectedBarber != null) {
            List<HairCut> haircuts = selectedBarber.getHaircuts();

            if (haircuts != null && !haircuts.isEmpty()) {
                ArrayAdapter<HairCut> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, haircuts);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                haircutsSpinner.setAdapter(adapter);
            } else {
                // Handle the case where there are no haircuts for the selected barber
                Toast.makeText(this, "No haircuts found for the selected barber.", Toast.LENGTH_SHORT).show();
                Log.e("NoHaircuts", "No haircuts found for the selected barber.");
            }
        }
    }

    public void goBackHome(View view) {
        Intent intent = new Intent(ApportionmentOrder.this, clientHomePage.class);
        intent.putExtra("Uid", client.getUid());
        startActivity(intent);
    }
}


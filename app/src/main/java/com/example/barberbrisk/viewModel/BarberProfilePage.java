package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.barberbrisk.R;
import com.example.barberbrisk.objects.Appointment;
import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.objects.HairCut;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class BarberProfilePage extends AppCompatActivity {
    private Barber barber;
    private final FirebaseFirestore db;
    boolean editMode = false;

    public BarberProfilePage() {
        db = FirebaseFirestore.getInstance();
    }

    EditText phonenumber;
    EditText BarberRate;
    EditText Name;
    Button saveEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_profile_page);

        phonenumber = findViewById(R.id.Phone_editText);
        BarberRate = findViewById(R.id.Barber_Rate_editText);
        Name = findViewById(R.id.editTextText);
        saveEdit = findViewById(R.id.edit_save_button);
        Button editprofile = findViewById(R.id.Barber_profile_edit_button);
        //write a message on the screen that the activity start
        Log.d("barberProfile", "start Barber profile activity");

        //create a obj of the first activity and take data about the user.
        Intent barberIntent = getIntent();
        String BarberUid = barberIntent.getStringExtra("Uid");

        assert BarberUid != null;
        Task<DocumentSnapshot> docRef = db.collection("Barbers")
                .document(BarberUid)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        barber = documentSnapshot.toObject(Barber.class);
                    } else {
                        Log.d("BarberSuccess", "Failed");
                    }


                    //set data on the activity


                    ///// prevent the user to edit
                    Name.setInputType(InputType.TYPE_NULL);
                    Name.setBackground(null);
                    phonenumber.setInputType(InputType.TYPE_NULL);
                    phonenumber.setBackground(null);
                    BarberRate.setInputType(InputType.TYPE_NULL);
                    BarberRate.setBackground(null);

                    phonenumber.setText(barber.getPhone());
                    Name.setText(barber.getName());
                    BarberRate.setText(String.valueOf(barber.getRating()));
                    editprofile.setOnClickListener(v -> onEditMod());
                    saveEdit.setOnClickListener(v -> saveEdit());

                });
    }

    public void updateRate() {

    }

    public void onEditMod() {
        if (editMode) {
            editMode = false;
            Name.setInputType(InputType.TYPE_NULL);
            Name.setBackground(null);
            phonenumber.setInputType(InputType.TYPE_NULL);
            phonenumber.setBackground(null);
//            BarberRate.setInputType(InputType.TYPE_NULL);
//            BarberRate.setBackground(null);
            saveEdit.setVisibility(View.INVISIBLE);
        } else {
            editMode = true;
            Name.setInputType(InputType.TYPE_CLASS_TEXT);
            Name.setBackground(ResourcesCompat.getDrawable(getResources(), android.R.drawable.editbox_background_normal, null));
            phonenumber.setInputType(InputType.TYPE_CLASS_TEXT);
            phonenumber.setBackground(ResourcesCompat.getDrawable(getResources(), android.R.drawable.editbox_background_normal, null));
//            BarberRate.setInputType(InputType.TYPE_CLASS_TEXT);
//            BarberRate.setBackground(ResourcesCompat.getDrawable(getResources(), android.R.drawable.editbox_background_normal, null));
            saveEdit.setVisibility(View.VISIBLE);
        }
    }

    public void saveEdit() {
        String name = Name.getText().toString();
        String phone = phonenumber.getText().toString();
        String rate = BarberRate.getText().toString();
        DocumentReference docRef = db.collection("Barbers").document(Objects.requireNonNull(barber.getUid()));
        docRef.update("name", name);
        docRef.update("phone", phone);
        docRef.update("rate", Double.parseDouble(rate));
        onEditMod();
        Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show();
    }


    public void arrowBackButton(View v) {
        Intent i = new Intent(BarberProfilePage.this, barberHomePage.class);
        startActivity(i);
    }

}
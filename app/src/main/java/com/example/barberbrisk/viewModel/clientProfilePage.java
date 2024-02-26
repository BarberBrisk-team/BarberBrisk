package com.example.barberbrisk.viewModel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.barberbrisk.R;
import com.example.barberbrisk.objects.Client;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class clientProfilePage extends AppCompatActivity {
    private Client myClient;
    private final FirebaseFirestore db;
    boolean editMode = false;

    TextView Name_editText;
    TextView Email_editText;
    TextView Phone_editText;

    Button Appointment_button;
    Button Edit_button;
    Button Save_button;
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

            //  set data on the activity
            Name_editText = findViewById(R.id.Name_editText);
            Email_editText = findViewById(R.id.Email_editText);
            Phone_editText = findViewById(R.id.phone_editText);
            Appointment_button = findViewById(R.id.buttonAppointHist);
            Edit_button = findViewById(R.id.edit_profile_button);
            Save_button = findViewById(R.id.save_client_edit_profile_button);

            Name_editText.setText(myClient.getName());
            Email_editText.setText(myClient.getEmail());
            Phone_editText.setText(myClient.getPhone());
            Appointment_button.setText("My Appointments");

            Name_editText.setInputType(InputType.TYPE_NULL);
            Name_editText.setBackground(null);
            Email_editText.setInputType(InputType.TYPE_NULL);
            Email_editText.setBackground(null);
            Phone_editText.setInputType(InputType.TYPE_NULL);
            Phone_editText.setBackground(null);

            Appointment_button.setVisibility(View.VISIBLE);
            Save_button.setVisibility(View.INVISIBLE);

            Edit_button.setOnClickListener(v -> onEditMod());
            Save_button.setOnClickListener(v -> saveEdit());

        });
    }

    public void onEditMod()
    {
        if(editMode)
        {
            editMode = false;
            Name_editText.setInputType(InputType.TYPE_NULL);
            Name_editText.setBackground(null);
            Email_editText.setInputType(InputType.TYPE_NULL);
            Email_editText.setBackground(null);
            Phone_editText.setInputType(InputType.TYPE_NULL);
            Phone_editText.setBackground(null);

            Appointment_button.setVisibility(View.VISIBLE);
            Save_button.setVisibility(View.INVISIBLE);
        }
        else
        {
            editMode = true;
            Name_editText.setInputType(InputType.TYPE_CLASS_TEXT);
            Name_editText.setBackground(ResourcesCompat.getDrawable(getResources(), android.R.drawable.editbox_background_normal, null));
            Email_editText.setInputType(InputType.TYPE_CLASS_TEXT);
            Email_editText.setBackground(null);
            Phone_editText.setInputType(InputType.TYPE_CLASS_TEXT);
            Phone_editText.setBackground(ResourcesCompat.getDrawable(getResources(), android.R.drawable.editbox_background_normal, null));

            Appointment_button.setVisibility(View.INVISIBLE);
            Save_button.setVisibility(View.VISIBLE);
        }
    }
    public void saveEdit()
    {
        myClient.setName(Name_editText.getText().toString());
        myClient.setPhone(Phone_editText.getText().toString());
        db.collection("Clients").document(myClient.getUid()).update("name", myClient.getName(), "phone", myClient.getPhone());
        onEditMod();
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
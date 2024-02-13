package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile_page);

//      write a message on the screen that the activity start
        Log.d("test", "start profile activity");

//      create a obj of the first activity and take data about the user.
        Intent clientIntent = getIntent();
        String ClientUid = clientIntent.getStringExtra("Uid");

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


//        set data on the activity
        Button b1 = findViewById(R.id.buttonName);
        Button b2 = findViewById(R.id.buttonEmail);
        Button b3 = findViewById(R.id.buttonPhone);
//        Button b4 = (Button)findViewById(R.id.buttonAppointHist);
        b1.setText(myClient.getName());
        b2.setText(myClient.getEmail());
        b3.setText(myClient.getPhone());
//        b4.setText(myClient.getAppointments().toString());
        });
    }

    public void arrowBackButton(View v){
        Intent i = new Intent(this, clientHomePage.class);
        startActivity(i);
    }
}
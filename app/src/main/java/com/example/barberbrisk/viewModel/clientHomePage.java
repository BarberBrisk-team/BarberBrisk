package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;
import com.example.barberbrisk.objects.Client;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class clientHomePage extends AppCompatActivity {
    private Client myObj;
    private Intent myIntent;
    private final FirebaseFirestore db;

    public clientHomePage() {
        db = FirebaseFirestore.getInstance();
    }
    /**
     * This function is called when the activity is created.
     * It gets the client details from the DB and sets the data on the activity.
     * @param savedInstanceState - the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home_page);
        myIntent = getIntent();
        // we need to fill all the object fields by the DB
        String ClientUid = myIntent.getStringExtra("Uid");
        DocumentReference docRef = db.collection("Clients").document(Objects.requireNonNull(ClientUid));
        docRef.get().addOnSuccessListener(documentSnapshot -> {
            Log.d("ClientSuccess", "Success");
            String email = (String) documentSnapshot.get("email");
            String name = (String) documentSnapshot.get("name");
            String password = (String) documentSnapshot.get("password");
            String phone = (String) documentSnapshot.get("phone");
            myObj = new Client(ClientUid, name, email, phone, password);
            Log.d("ClientSuccess", "Success2");
        });

    }
    /**
     * This function is called when the user clicks on the " Profile" button.
     * It opens the clientProfilePage activity.
     * @param v - the view
     */
    public void handelButtonProfile(View v){
        myIntent = new Intent(clientHomePage.this, clientProfilePage.class);
        myIntent.putExtra("Uid", myObj.getUid());
        startActivity(myIntent);
    }
    /**
     * This function is called when the user clicks on the " Appointment Order" button.
     * It opens the ApportionmentOrder activity.
     * @param n - the view
     */
    public void handelButtonOrder(View n){
        myIntent = new Intent(clientHomePage.this, ApportionmentOrder.class);
        myIntent.putExtra("Uid", myObj.getUid());
        startActivity(myIntent);
    }
}
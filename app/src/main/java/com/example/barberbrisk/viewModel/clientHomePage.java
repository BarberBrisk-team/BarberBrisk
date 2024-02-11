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

public class clientHomePage extends AppCompatActivity {
    private Client myObj;
    private Intent myIntent;
    private final FirebaseFirestore db;

    public clientHomePage() {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home_page);
        myIntent = getIntent();
        //      we need to fill all the object fields by the DB
        String ClientUid = LogInPage.getUid();
        DocumentReference docRef = db.collection("Clients").document(ClientUid);
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
    public void handelButtonProfile(View v){
        startActivity(new Intent(clientHomePage.this, clientProfilePage.class));
        myIntent.putExtra("myobj",myObj);
    }

    public void handelButtonOrder(View n){
        startActivity(new Intent(clientHomePage.this, appionment_oredr.class));
        myIntent.putExtra("myobj",myObj);
    }
}
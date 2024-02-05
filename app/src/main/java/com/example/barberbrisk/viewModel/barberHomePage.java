package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;
import com.example.barberbrisk.objects.Barber;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class barberHomePage extends AppCompatActivity {
    private Barber myObj;
    private Intent myIntent;
    private final FirebaseFirestore db;

    public barberHomePage() {
        db = FirebaseFirestore.getInstance();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_home_page);
        myIntent = getIntent();
//      we need to fill all the object fields by the DB
        String ClientUid = LogInPage.getUid();
        DocumentReference docRef = db.collection("Barbers").document(ClientUid);
        docRef.get().addOnSuccessListener(documentSnapshot -> {
            Log.d("ClientSuccess", "Success");
            myObj = documentSnapshot.toObject(Barber.class);
        });
    }
    public void handelButtonProfile(View v){
        myIntent.putExtra("myobj",myObj);
        startActivity(new Intent(barberHomePage.this, barBerProfilePage.class));
    }

    public void handelButtonOrder(View n){
        myIntent.putExtra("myobj",myObj);
        startActivity(new Intent(barberHomePage.this, appointment_order.class));
    }

}
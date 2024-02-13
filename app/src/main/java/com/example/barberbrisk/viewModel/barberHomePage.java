package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;
import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.objects.HairCut;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

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
        String BarberUid = myIntent.getStringExtra("Uid");
        DocumentReference docRef = db.collection("Barbers").document(BarberUid);
        docRef.get().addOnSuccessListener(documentSnapshot -> {
            Log.d("BarberSuccess", "Success");
            String email = (String) documentSnapshot.get("email");
            String name = (String) documentSnapshot.get("name");
            String password = (String) documentSnapshot.get("password");
            String phone = (String) documentSnapshot.get("phone");
            Double rate = (Double) documentSnapshot.get("rate");
            List<HairCut> haircuts = (List<HairCut>) documentSnapshot.get("haircuts");
            myObj = new Barber(BarberUid, name, email, phone, password, rate , haircuts);
            Log.d("BarberSuccess", "Success2");
        });
    }
    public void handelButtonProfile(View v){
        myIntent.putExtra("myobj",myObj);
        startActivity(new Intent(barberHomePage.this, barBerProfilePage.class));
    }

    public void handelButtonOrder(View n){
        myIntent.putExtra("myobj",myObj);
//        startActivity(new Intent(barberHomePage.this, barberHomePage.class));
    }

}
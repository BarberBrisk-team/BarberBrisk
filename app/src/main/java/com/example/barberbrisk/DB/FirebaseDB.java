package com.example.barberbrisk.DB;

import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.objects.Client;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class FirebaseDB {



        protected FirebaseFirestore mDB;
        protected FirebaseAuth mAuth;

        public FirebaseDB(){
            mDB = FirebaseFirestore.getInstance();
            mAuth = FirebaseAuth.getInstance();

    }
    //register new user to firebase
    public void registerNewUser(String email, String password , OnCompleteListener<AuthResult> oc){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(oc);
    }
    public String getUID(){
        return Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
    }
    //put new barber in DB
    public void putNewBar(String uid, String email, String password, String name, String phone){
        Barber barber = new Barber(name,email,phone,password);
        mDB.collection("Barbers").document(uid).set(barber);
    }
    //put new client in DB
    public void putNewClient(String uid, String email, String password, String name, String phone){
        Client client = new Client(uid, name,email,phone,password);
        mDB.collection("Clients").document(uid).set(client);
    }
}

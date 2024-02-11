package com.example.barberbrisk.DB;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class AuthenticationDB {

    private final FirebaseAuth mAuth;

    public AuthenticationDB() {
        mAuth = FirebaseAuth.getInstance();
    }

    //register new user to firebase
    public void registerNewUser(String email, String password, OnCompleteListener<AuthResult> oc) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(oc);
    }

    public String getUID() {
        return Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
    }

}

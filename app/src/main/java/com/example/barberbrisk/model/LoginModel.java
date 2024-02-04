package com.example.barberbrisk.model;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginModel {

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    public LoginModel() {
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public void loginUser(String email, String password, OnUserLoggedInListener listener) {
        Log.d("AuthTestingInModel", "email: " + email + " password " + password);
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String uid = auth.getCurrentUser().getUid();
                        Log.d("AuthTesting", "UID: " + uid);
                        db.collection("Barbers").document(uid).get()
                                .addOnSuccessListener(documentSnapshot -> {
                                    if (documentSnapshot.exists()) {
                                        listener.onUserLoggedIn("Barber");
                                    } else {
                                        db.collection("Clients").document(uid).get()
                                                .addOnSuccessListener(documentSnapshotClient -> {
                                                    if (documentSnapshotClient.exists()) {
                                                        listener.onUserLoggedIn("Client");
                                                    }
                                                })
                                                .addOnFailureListener(e -> {
                                                    listener.onFailure(e.getMessage());
                                                });
                                    }
                                })
                                .addOnFailureListener(e -> {
                                    listener.onFailure(e.getMessage());
                                });
                    } else {
                        Log.d("AuthTestingFailed", "couldn't log in");
                        listener.onFailure(task.getException().getMessage());
                    }
                });
    }

    public interface OnUserLoggedInListener {
        void onUserLoggedIn(String userType);
        void onFailure(String errorMessage);
    }
}

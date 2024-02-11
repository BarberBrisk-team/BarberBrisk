package com.example.barberbrisk.model;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

/**
 * This class represents the model for the login process.
 * It handles the authentication of the user using Firebase Authentication and Firestore.
 */
public class LoginModel {

    private final FirebaseAuth auth;
    private final FirebaseFirestore db;
    private String Uid;
    /**
     * Constructor for the LoginModel class.
     * It initializes FirebaseAuth and FirebaseFirestore instances.
     */
    public LoginModel() {
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        Uid = "";
    }

    /**
     * This method is used to authenticate the user using their email and password.
     * If the authentication is successful, it checks if the user is a Barber or a Client by querying the Firestore database.
     * It then calls the appropriate method of the OnUserLoggedInListener.
     *
     * @param email The email entered by the user.
     * @param password The password entered by the user.
     * @param listener An instance of OnUserLoggedInListener. Its methods will be called based on the result of the authentication process.
     */
    public void loginUser(String email, String password, OnUserLoggedInListener listener) {
        Log.d("AuthTestingInModel", "email: " + email + " password " + password);
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String uid = Objects.requireNonNull(auth.getCurrentUser()).getUid();
                        this.Uid = uid;
                        Log.d("AuthTesting", "UID: " + uid);
                        db.collection("Barbers").document(uid).get()
                                .addOnSuccessListener(documentSnapshot -> {
                                    if (documentSnapshot.exists()) {
                                        listener.onUserLoggedIn("Barber");
                                    } else {
                                        db.collection("Clients").document(uid).get()
                                                .addOnSuccessListener(documentSnapshotClient -> {
                                                    if (documentSnapshotClient.exists()) {
                                                        Log.d("AuthTesting", "Check if enter client");
                                                        listener.onUserLoggedIn("Client");
                                                    }
                                                })
                                                .addOnFailureListener(e -> listener.onFailure(e.getMessage()));
                                    }
                                })
                                .addOnFailureListener(e -> listener.onFailure(e.getMessage()));
                    } else {
                        Log.d("AuthTestingFailed", "couldn't log in");
                        listener.onFailure(Objects.requireNonNull(task.getException()).getMessage());
                    }
                });
    }

    /**
     * This interface is used as a callback for the loginUser method.
     * It provides methods that will be called based on the result of the authentication process.
     */
    public interface OnUserLoggedInListener {
        /**
         * This method is called when the user is successfully logged in.
         * It receives a string indicating the user type (either "Barber" or "Client").
         *
         * @param userType A string indicating the user type. It can be either "Barber" or "Client".
         */
        void onUserLoggedIn(String userType);
        /**
         * This method is called when the authentication process fails.
         * It receives a string containing the error message.
         *
         * @param errorMessage A string containing the error message.
         */
        void onFailure(String errorMessage);
    }
    public String getUid(){
        return this.Uid;
    }
}

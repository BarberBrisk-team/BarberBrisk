package com.example.barberbrisk.objects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Client extends User{

    private String password;

    //    Constructor for barber don't have a list haircuts to upload
    public Client(String uid, String FirstName, String LastName, String email, String phone, String password) {
        super(uid,FirstName, LastName, email, phone);
        this.password = password;
    }


    // Constructor for Parcelable
    protected Client(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };
    @NonNull
    @Override
    public String toString() {
        String FirstName = super.getFirstName();
        String LastName = super.getLastName();
        return "Client{" + "First Name='" +
                FirstName + '\'' + "Last Name='" + LastName + '\'' +
                "password='" + password + '\'' +

//                ", haircuts=" + haircuts +
                '}';
    }
}
package com.example.barberbrisk.objects;

import android.os.Parcel;

public class Client extends User {

    public Client(Parcel in) {
        super(in);
    }

    public Client(String uid, String name, String email, String phone, String password) {

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
}
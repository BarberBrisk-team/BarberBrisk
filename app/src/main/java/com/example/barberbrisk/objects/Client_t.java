package com.example.barberbrisk.objects;

import android.os.Parcel;

public class Client_t extends User {


    protected Client_t(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    public static final Creator<Client_t> CREATOR = new Creator<Client_t>() {
        @Override
        public Client_t createFromParcel(Parcel in) {
            return new Client_t(in);
        }

        @Override
        public Client_t[] newArray(int size) {
            return new Client_t[size];
        }
    };
}
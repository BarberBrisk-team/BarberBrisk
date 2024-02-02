package com.example.barberbrisk.objects;

import android.os.Parcel;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class Barber extends User {
    private String password;
    private Double rate;
    List<HairCut> haircuts = new ArrayList<HairCut>();

    //    Constructor for barber don't have a list haircuts to upload
    public Barber(String FirstName, String LastName, String email, String phone, String password) {
        super(FirstName, LastName, email, phone);
        this.rate = 5.0;
        this.password = password;
    }

    public Barber(String FirstName, String LastName, String email, String phone, Double rate, List<HairCut> haircuts) {
        super(FirstName, LastName, email, phone);
        this.rate = rate;
        this.haircuts = haircuts;
    }

    @NonNull
    @Override
    public String toString() {
        return "Barber{" +
                "password='" + password + '\'' +
                ", rate=" + rate +
                ", haircuts=" + haircuts +
                '}';
    }

    public Barber(String firstName, String lastName, String phoneNumber, Double rate) {
        super(firstName, lastName, phoneNumber);
        this.rate = rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public void setHaircuts(List<HairCut> haircuts) {
        this.haircuts = haircuts;
    }

    public Double getRate() {
        return rate;
    }

    public List<HairCut> getHaircuts() {
        return haircuts;
    }
/*
    // Constructor for Parcelable
    protected Barber(Parcel in) {
        super(in);
        this.haircuts = in.readArrayList(null);
        this.rate = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    public static final Creator<Barber> CREATOR = new Creator<Barber>() {
        @Override
        public Barber createFromParcel(Parcel in) {
            return new Barber(in);
        }

        @Override
        public Barber[] newArray(int size) {
            return new Barber[size];
        }
    };*/
}


package com.example.barberbrisk.objects;

import android.os.Parcel;

import java.util.ArrayList;
import java.util.List;

public class Barber extends User {
    private Double rate;
    List<HairCut> haircuts = new ArrayList<>();
    private String password;

    //    Constructor for barber don't have a list haircuts to upload
    public Barber(String uid, String name, String email, String phone, String password) {
        super(uid,name, email, phone , password);
        this.rate = 5.0;

    }


    public Barber(String Uid, String name, String email, String phone, String password, Double rate, List<HairCut> haircuts) {
        super(Uid, name, email, phone, password);
        this.rate = rate;
        this.haircuts = haircuts;
    }

    public Barber() {

    }

//savfsg
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
    };
//    @NonNull
//    @Override
//    public String toString() {
//        String FirstName = super.getFirstName();
//        String LastName = super.getLastName();
//        return "Barber{" + "First Name='" +
//                FirstName + '\'' + "Last Name='" + LastName + '\'' +
//                "password='" + password + '\'' +
//                ", rate=" + rate +
////                ", haircuts=" + haircuts +
//                '}';
//    }
}


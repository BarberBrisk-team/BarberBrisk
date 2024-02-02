package com.example.barberbrisk.objects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Client implements Parcelable{

    private String uid;
    private String FirstName;
    private String LastName;
    private String email;
    private String phone;
    private String password;



/*
    public Client(String uid, String FullName, String email, String phone, String password) {
        this.uid = uid;
        String [] Arr_FullName =  FullName.split(" ");
        this.FirstName = Arr_FullName[0];
        this.LastName = Arr_FullName[1];
        this.email = email;
        this.phone = phone;
        this.password = password;
    }*/

    public Client(String uid, String FirstName,String LastName, String email, String phone)
    {
        this.uid = uid;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.email = email;
        this.phone = phone;
    }


    protected Client(Parcel in) {
        uid = in.readString();
        FirstName = in.readString();
        LastName = in.readString();
        email = in.readString();
        phone = in.readString();
        password = in.readString();
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password){this.password = password;}

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword(){return password;}


    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @NonNull
    public String toString() {
        return String.format("Client[uid=%s, FirstName=%s, LastName=%s, email=%s, phone=%s, password=%s]", uid, FirstName, LastName, email, phone, password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(FirstName);
        dest.writeString(LastName);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(password);
    }
}
package com.example.barberbrisk.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String uid;
    private String FirstName;
    private String LastName;
    private String email;
    private String phone;
    private String password;


    protected User(Parcel in) {
        FirstName = in.readString();
        email = in.readString();
        phone = in.readString();
        password = in.readString();
    }
    public User(){

    }
    public User(String name, String email, String phone, String password) {
        this.FirstName = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

//    public User(String name, String email, String phone) {
//    }
    public User(String FirstName, String LastName, String PhoneNumber){
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.phone = PhoneNumber;
    }
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password){this.password = password;}

    public String getFirstName() {
        return FirstName;
    }
    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword(){return password;}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(FirstName);
        dest.writeString(LastName);
        dest.writeString(email);
        dest.writeString(phone);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
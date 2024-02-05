package com.example.barberbrisk.objects;

import android.os.Parcel;
import android.os.Parcelable;
public class User implements Parcelable {

    private String uid;
    private String name;
    private String email;
    private String phone;
    private String password;



    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
        phone = in.readString();
        password = in.readString();
    }
    public User(){

    }
    public User(String uid, String name, String email, String phone, String password) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

//    public User(String name, String email, String phone) {
//    }
    public User(String name,String  PhoneNumber, String password){
        this.name = name;
        this.phone = PhoneNumber;
        this.password = password;

    }
    public void setName(String Name) {
        this.name = Name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password){this.password = password;}
    public void setUid(String Uid){this.uid = Uid;}

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword(){return password;}

    public String getUid(){return uid;}
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
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
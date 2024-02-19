package com.example.barberbrisk.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class ClientAppointment implements Parcelable {

    private String AppointmentUid;
    private String ClientUid;
    private String HairstyleUid;




    protected ClientAppointment(Parcel in) {
        AppointmentUid = in.readString();
        ClientUid = in.readString();
        HairstyleUid = in.readString();
    }
    public ClientAppointment(){

    }

    public ClientAppointment(String AppointmentUid, String ClientUid, String HairstyleUid)
    {
        this.AppointmentUid = AppointmentUid;
        this.ClientUid = ClientUid;
        this.HairstyleUid = HairstyleUid;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ClientUid);
        dest.writeString(HairstyleUid);
        dest.writeString(AppointmentUid);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ClientAppointment> CREATOR = new Creator<ClientAppointment>() {
        @Override
        public ClientAppointment createFromParcel(Parcel in) {
            return new ClientAppointment(in);
        }

        @Override
        public ClientAppointment[] newArray(int size) {
            return new ClientAppointment[size];
        }
    };

    public String getAppointmentUid() {
        return AppointmentUid;
    }

    public void setAppointmentUid(String appointmentUid) {
        AppointmentUid = appointmentUid;
    }

    public String getClientUid() {
        return ClientUid;
    }

    public void setClientUid(String clientUid) {
        ClientUid = clientUid;
    }

    public String getHairstyleUid() {
        return HairstyleUid;
    }

    public void setHairstyleUid(String hairstyleUid) {
        HairstyleUid = hairstyleUid;
    }
}
package com.example.barberbrisk.objects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Appointment implements Parcelable {

    private String BarberUid;
    private Timestamp TimeAndDate;
    private boolean avilability;

    public Appointment(String BarberUid, Timestamp TimeAndDate, boolean avilability)
    {
        this.BarberUid = BarberUid;
        this.TimeAndDate = TimeAndDate;
        this.avilability = avilability;
    }
    public Appointment(String BarberUid, Date date, Time time, boolean avilability)
    {
        this.BarberUid = BarberUid;
        this.TimeAndDate = dateAndTime_to_timestamp(date,time);
        this.avilability = avilability;
    }

    public Timestamp dateAndTime_to_timestamp(Date date,Time time)
    {
        long dateTime = date.getTime() + time.getTime(); // get the time in milliseconds
        Timestamp timestamp = new Timestamp(dateTime); // convert to Timestamp
        System.out.println(timestamp); // print the Timestamp
        return timestamp;
    }

    protected Appointment(Parcel in) {
        BarberUid = in.readString();
        avilability = in.readByte() != 0;
    }

    public static final Creator<Appointment> CREATOR = new Creator<Appointment>() {
        @Override
        public Appointment createFromParcel(Parcel in) {
            return new Appointment(in);
        }

        @Override
        public Appointment[] newArray(int size) {
            return new Appointment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(BarberUid);
        dest.writeByte((byte) (avilability ? 1 : 0));
    }
}

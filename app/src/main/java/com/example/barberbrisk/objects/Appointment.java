package com.example.barberbrisk.objects;

import android.os.Parcel;
import android.os.Parcelable;
import java.sql.Timestamp;
import java.util.Date;

public class Appointment implements Parcelable {

    private String BarbarID;
    private Timestamp TimeAndDate;
    private boolean available;




    protected Appointment(Parcel in) {
        BarbarID = in.readString();
        TimeAndDate = new Timestamp(in.readLong());
        available = in.readByte() != 0;
    }
    public Appointment(){

    }

    public Appointment(Appointment appointment)
    {
        this.BarbarID = appointment.BarbarID;
        this.TimeAndDate = appointment.TimeAndDate;
        this.available = appointment.available;
    }

    /**
     *
     * @param BarbarID
     * @param TimeAndDate
     * @param available
     */
    public Appointment(String BarbarID,Timestamp TimeAndDate, boolean available) {
        this.BarbarID = BarbarID;
        this.TimeAndDate = TimeAndDate;
        this.available = available;

    }

    /**
     *
     * @param BarbarID
     * @param TimeAndDate_TextFormat (yyyy-MM-dd HH:mm:ss)
     * @param available
     */
    public Appointment(String BarbarID,String TimeAndDate_TextFormat, boolean available) {
        this.BarbarID = BarbarID;
        Timestamp timestamp1 = Timestamp.valueOf(TimeAndDate_TextFormat);
        this.TimeAndDate = TimeAndDate;
        this.available = available;

    }

//    public void setTimeAndDate(Timestamp TimeAndDate) {
//        this.TimeAndDate = TimeAndDate;
//    }
    public void setTimeAndDate(Date TimeAndDate) {
        this.TimeAndDate = new Timestamp(TimeAndDate.getTime());
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }


    public void setBarbarID(String Uid){this.BarbarID = Uid;}

    public Timestamp getTimeAndDate() {
        return TimeAndDate;
    }
    public boolean getAvailable() {
        return available;
    }
    public String getBarbarID(){return BarbarID;}
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(TimeAndDate.getTime());
        dest.writeByte((byte) (available ? 1 : 0));
        dest.writeString(BarbarID);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
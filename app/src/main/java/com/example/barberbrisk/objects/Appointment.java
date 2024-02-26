package com.example.barberbrisk.objects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class Appointment {
    final private String appointmentUid = UUID.randomUUID().toString(); // unique id for each appointment
    Date TimeAndDate; // the time and date of the appointment
    private boolean available; // is the appointment available
    HairCut hairCut; // the kind of haircut that the appointment is for

    private String clientName;
    private String barberName;
    public String getBarberName() {
        return barberName;
    }
    public void setBarberName(String barberName) {
        this.barberName = barberName;
    }
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     *
     * @param timeAndDate the time of the appointment
     * @param available indicates if the appointment is available or not
     */
    public Appointment(Timestamp timeAndDate, boolean available) {
        TimeAndDate = timeAndDate; // the time and date of the appointment
        this.available = available; // is the appointment available
        this.hairCut = null; // the kind of haircut that the appointment is for
        this.clientName = null;
    }

    public Appointment() {

    }

    /**
     * getter for the appointmentUid
     *
     * @return string of the appointmentUid
     */
    public String getAppointmentUid() {
        return appointmentUid;
    }

    /**
     * getter for the TimeAndDate
     *
     * @return the TimeAndDate of the appointment in Timestamp
     */
    public Date getTimeAndDate() {
        return TimeAndDate;
    }

    /**
     *
     * @param timeAndDate the time of the appointment
     */
    public void setTimeAndDate(Date timeAndDate) {
        TimeAndDate = timeAndDate;
    }

    /**
     *
     * @return the availability of the appointment
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * setter for the available
     *
     * @param available to set the new availability of the appointment
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * getter for the hair Cut
     *
     * @return hairCut
     */
    public HairCut getHairCut() {
        return hairCut;
    }

    /**
     * setter for the hair Cut
     *
     * @param hairCut the new haircut
     */
    public void setHairCut(HairCut hairCut) {
        this.hairCut = hairCut;
    }

    /**
     * toString method for the appointment
     *
     */
    @NonNull
    @Override
    public String toString() {
        return "Appointment_combined_version{" +
                "appointmentUid='" + appointmentUid + '\'' +
                ", TimeAndDate=" + TimeAndDate.toString() +
                ", available=" + available +
                ", hairCut=" + (hairCut != null ? hairCut.toString() : "null") +
                '}';
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable instance's marshaled representation.
     *
     */
    public Appointment(Parcel in) {
        TimeAndDate = new Timestamp(in.readLong());
        available = in.readByte() != 0;
        hairCut = in.readParcelable(HairCut.class.getClassLoader());
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(TimeAndDate.getTime());
        dest.writeByte((byte) (available ? 1 : 0));
        dest.writeParcelable(hairCut, flags);
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable instance's marshaled representation.
     * a bitmask indicating the set of special object types marshaled by this Parcelable object instance.
     */
    public static final Parcelable.Creator<Appointment> CREATOR = new Parcelable.Creator<Appointment>() {
        public Appointment createFromParcel(Parcel in) {
            return new Appointment(in);
        }

        /**
         * Create a new array of the Parcelable class.
         * @param size Size of the array.
         * @return the size of the appointments array
         */
        public Appointment[] newArray(int size) {
            return new Appointment[size];
        }
    };
}

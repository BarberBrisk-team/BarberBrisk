package com.example.barberbrisk.objects;

import android.os.Parcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//add default list of haircuts


public class Barber extends User {

    private Double rate;
    List<HairCut> haircuts = new ArrayList<>();

    private HashMap<String, Appointment> availableAppointments = new HashMap<>();
    private HashMap<String, Appointment> occupiedAppointments = new HashMap<>();

    //    Constructor for barber don't have a list haircuts to upload
    public Barber(String uid, String name, String email, String phone, String password) {
        super(uid, name, email, phone, password);
        this.rate = 5.0;
        this.availableAppointments = new HashMap<>();
        this.occupiedAppointments = new HashMap<>();
        //add default list of haircuts
        this.haircuts = new ArrayList<>();

    }
    //constructor for barber that have a list haircuts to upload

    public Barber(String uid, String name, String email, String phone, Double rate, List<HairCut> haircuts ) {
        super(name, email, phone);
        this.rate = rate;
        this.haircuts = haircuts;
    }


    public Barber() {

    }

    public Barber(String barberUid, String name, String email, String phone, String password, Double rate, List<HairCut> haircuts ) {
        super(barberUid, name, email, phone, password);
        this.rate = rate;
        this.haircuts = haircuts;

    }

    public void addAvailableAppointment(Appointment appointment) {
        if (appointment.isAvailable()) {
            availableAppointments.put(appointment.getAppointmentUid(), appointment);
        }
    }

    public void setAvailableAppointments(HashMap<String, Appointment> availableAppointments) {
        this.availableAppointments = availableAppointments;
    }

    public void setOccupiedAppointments(HashMap<String, Appointment> occupiedAppointments) {
        this.occupiedAppointments = occupiedAppointments;
    }

    public void removeAvailableAppointment(Appointment appointment) {
        availableAppointments.remove(appointment.getAppointmentUid());
    }

    public void removeAvailableAppointment(String appointmentUid) {
        availableAppointments.remove(appointmentUid);
    }

    public void addOccupiedAppointment(Appointment appointment) {
        if (!appointment.isAvailable()) {
            occupiedAppointments.put(appointment.getAppointmentUid(), appointment);
        }
    }

    public void removeOccupiedAppointment(Appointment appointment) {
        occupiedAppointments.remove(appointment.getAppointmentUid());
    }

    public void removeOccupiedAppointment(String appointmentUid) {
        occupiedAppointments.remove(appointmentUid);
    }

    public HashMap<String, Appointment> getAvailableAppointments() {
        return availableAppointments;
    }

    public HashMap<String, Appointment> getOccupiedAppointments() {
        return occupiedAppointments;
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

    public void addHaircut(HairCut haircut) {
        haircuts.add(haircut);
    }

    public void removeHaircut(HairCut haircut) {
        haircuts.remove(haircut);
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


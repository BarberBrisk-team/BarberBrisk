package com.example.barberbrisk.objects;

import android.os.Parcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//add default list of haircuts


public class Barber extends User {

    private Double rating;
    List<HairCut> hairCuts = new ArrayList<>();



    private int numOfRaters = 1;

    private HashMap<String, Appointment> availableAppointments = new HashMap<>();
    private HashMap<String, Appointment> occupiedAppointments = new HashMap<>();

    //    Constructor for barber don't have a list haircuts to upload
    public Barber(String uid, String name, String email, String phone, String password) {
        super(uid, name, email, phone, password);
        this.rating = 5.0;
        this.availableAppointments = new HashMap<>();
        this.occupiedAppointments = new HashMap<>();
        //add default list of haircuts
        this.hairCuts = new ArrayList<>();


    }
    //constructor for barber that have a list haircuts to upload

    public Barber(String uid, String name, String email, String phone, Double rating, List<HairCut> hairCuts) {
        super(name, email, phone);
        this.rating = rating;
        this.hairCuts = hairCuts;
    }
    public int getNumOfRaters() {
        return numOfRaters;
    }

    public void setNumOfRaters(int numOfRaters) {
        this.numOfRaters = numOfRaters;
    }

    public void addRating(Double rating) {
        this.rating = (this.rating * numOfRaters + rating) / (numOfRaters + 1);
        numOfRaters++;
    }


    public Barber() {

    }

    public Barber(String barberUid, String name, String email, String phone, String password, Double rating, List<HairCut> hairCuts) {
        super(barberUid, name, email, phone, password);
        this.rating = rating;
        this.hairCuts = hairCuts;

    }

    public void addAvailableAppointment(Appointment appointment) {
        if (appointment.isAvailable()) {
            appointment.setBarberName(this.getName());
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


    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setHairCuts(List<HairCut> hairCuts) {
        this.hairCuts = hairCuts;
    }

    public Double getRating() {
        return rating;
    }

    public List<HairCut> getHairCuts() {
        return hairCuts;
    }

    public void addHaircut(HairCut haircut) {
        hairCuts.add(haircut);
    }

    public void removeHaircut(HairCut haircut) {
        hairCuts.remove(haircut);
    }

    // Constructor for Parcelable
    protected Barber(Parcel in) {
        super(in);
        this.hairCuts = in.readArrayList(null);
        this.rating = in.readDouble();
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


package com.example.barberbrisk.objects;

import android.os.Parcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Barber extends User {

    private Double rate;
    List<HairCut> haircuts = new ArrayList<HairCut>();

    private HashMap<String, Appointment_combined_version> AvailableAppointments = new HashMap<>();
    private HashMap<String, Appointment_combined_version> OccupiedAppointments = new HashMap<>();

    //    Constructor for barber don't have a list haircuts to upload
    public Barber(String uid, String name, String email, String phone, String password) {
        super(uid, name, email, phone, password);
        this.rate = 5.0;
        this.AvailableAppointments = new HashMap<>();
        this.OccupiedAppointments = new HashMap<>();
    }
    //constructor for barber that have a list haircuts to upload

    public Barber(String name, String email, String phone, Double rate, List<HairCut> haircuts) {
        super(name, email, phone);
        this.rate = rate;
        this.haircuts = haircuts;
    }

    public Barber() {

    }

    public void addAvailableAppointment(Appointment_combined_version appointment) {
        if (appointment.isAvailable()) {
            AvailableAppointments.put(appointment.getAppointmentUid(), appointment);
        }
    }

    public void removeAvailableAppointment(Appointment_combined_version appointment) {
        AvailableAppointments.remove(appointment.getAppointmentUid());
    }

    public void removeAvailableAppointment(String appointmentUid) {
        AvailableAppointments.remove(appointmentUid);
    }

    public void addOccupiedAppointment(Appointment_combined_version appointment) {
        if (!appointment.isAvailable()) {
            OccupiedAppointments.put(appointment.getAppointmentUid(), appointment);
        }
    }

    public void removeOccupiedAppointment(Appointment_combined_version appointment) {
        OccupiedAppointments.remove(appointment.getAppointmentUid());
    }

    public void removeOccupiedAppointment(String appointmentUid) {
        OccupiedAppointments.remove(appointmentUid);
    }

    public HashMap<String, Appointment_combined_version> getAvailableAppointments() {
        return AvailableAppointments;
    }

    public HashMap<String, Appointment_combined_version> getOccupiedAppointments() {
        return OccupiedAppointments;
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

    public List<HairCut> getHairCutList() {
        return haircuts;
    }
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


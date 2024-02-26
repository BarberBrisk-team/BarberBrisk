package com.example.barberbrisk.objects;

import android.os.Parcel;

import java.util.HashMap;

public class Client extends User {
    HashMap<String, Appointment> appointments = new HashMap<>();

    public Client(Parcel in) {
        super(in);
    }

    public Client() {

    }
    public Client(String uid, String name, String email, String phone, String password) {
        super(uid, name, email, phone, password);
        this.appointments = new HashMap<>();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
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

    /**
     * Get appointments of the client
     * @return the appointments hashmap
     */
    public HashMap<String, Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Set appointments of the client
     * @param appointments the appointments hashmap
     */
    public void setAppointments(HashMap<String, Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     * Add appointment to the client
     * @param appointment indicates the new appointment
     */
    public void addAppointment(Appointment appointment) {
        appointment.setAvailable(false);
        appointment.setClientName(this.getName());
        appointments.put(appointment.getAppointmentUid(), appointment);
    }
}
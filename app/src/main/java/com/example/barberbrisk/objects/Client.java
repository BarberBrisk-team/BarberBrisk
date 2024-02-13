package com.example.barberbrisk.objects;

import android.os.Parcel;

import java.util.HashMap;

public class Client extends User {
    HashMap<String, Appointment_combined_version> Appointments = new HashMap<>();

    public Client(Parcel in) {
        super(in);
    }

    public Client(String uid, String name, String email, String phone, String password) {
        super(uid, name, email, phone, password);
        this.Appointments = new HashMap<>();
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
     * @return
     */
    public HashMap<String, Appointment_combined_version> getAppointments() {
        return Appointments;
    }

    /**
     * Set appointments of the client
     * @param appointments
     */
    public void setAppointments(HashMap<String, Appointment_combined_version> appointments) {
        Appointments = appointments;
    }

    /**
     * Add appointment to the client
     * @param appointment
     */
    public void addAppointment(Appointment_combined_version appointment) {
        appointment.setAvailable(false);
        Appointments.put(appointment.getAppointmentUid(), appointment);
    }
}
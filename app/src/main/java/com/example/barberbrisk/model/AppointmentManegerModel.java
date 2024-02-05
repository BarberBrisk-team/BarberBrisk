package com.example.barberbrisk.model;

import com.example.barberbrisk.DB.DataBase;
import com.example.barberbrisk.objects.Appointment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to manage the appointments.
 * it will run the the queries to the appointment Lists.
 */
public class AppointmentManegerModel {

    /**
     * This method is used to filter the avilible appointments by the barber ID.
     */
    public static Map<String,Appointment> filterAvilibleAppointmentsByBarberID(String barberID) {
        Map<String ,Appointment> avilibleAppointments = new HashMap<>();
        if(DataBase.getAppointmentList() == null){
            DataBase.DownloadListAppoinment();
        }
        Map<String, Appointment> AppointmentList = DataBase.getAppointmentList();
        AppointmentList.forEach((k,v)->{
            if(v.getBarbarID().equals(barberID) && v.getAvailable() == true){
                avilibleAppointments.put(k,v);
            }
        });
        return avilibleAppointments;
    }

    /**
     * This method is used to set an appointment for the client with client appointment.
     * it will set the appointment as aviilible = false, get hairstylist id and the client id.
     * and create new client appointment.
     */
    public static void setClientAppointment(String appointmentID, String clientID, String hairstylistID) {
//        DataBase.SetClientAppointment(appointmentID, clientID, hairstylistID);
    }

}

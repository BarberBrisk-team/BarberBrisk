package com.example.barberbrisk.model;

import com.example.barberbrisk.DB.DataBase;
import com.example.barberbrisk.objects.Appointment;
import com.example.barberbrisk.objects.HairCut;

import java.sql.Time;
import java.util.Date;
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
            if(v.getBarbaraID().equals(barberID) && v.getAvailable() == true){
                avilibleAppointments.put(k,v);
            }
        });
        return avilibleAppointments;
    }


    /**
     * This method is used to filter the hairstyle for the Barber hairstyle list.
     */
    public static List<HairCut> filterHairstyleByBarberID(String barberID) {
        return DataBase.getBaraberlist().get(barberID).getHairCutList();
    }

    /*
        get the date and time and check and the barber id and check if the appointment is available
     */
    public static boolean isAppointmentAvailable(String barberID, String ClientID, String HairstyleID,
                                                 Date date, Time time) {
          Map<String, Appointment> AppointmentList = filterAvilibleAppointmentsByBarberID(barberID);
          for(String key : AppointmentList.keySet()){
              Appointment ap = AppointmentList.get(key);
              date.setTime(date.getTime() + time.getTime());
              if(ap.getAvailable() == true && ap.getTimeAndDate().equals(date)){
                  setClientAppointment(new ClientAppointment(ClientID,barberID,HairstyleID));
                  return true;
              }
          }
        return false;
    }
    /**
     * This method is used to set an appointment for a client.
     */
    public static void setClientAppointment(ClientAppointment clientAppointment) {

        Appointment ap = DataBase.getAppointmentList().get(clientAppointment.getAppointmentUid());
        ap.setAvailable(false);
        DataBase.UpdateBarberAppointments(clientAppointment.getAppointmentUid(),ap);
        DataBase.SetClientAppointment(clientAppointment);
    }

}

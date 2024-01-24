package com.example.barberbrisk;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class DataBase {
    /**
     * This method is used to add a new barber to the database.
     * @param FirstName is the first name of the barber.
     * @param LastName is the last name of the barber.
     * @param PhoneNumber is the phone number of the barber.
     * @param ImageFile is a face shot of the barber.
     */
    public static void NewBarber(String FirstName, String LastName, int PhoneNumber, File ImageFile) {

    }

    /**
     * This method is used to add a new customer to the database.
     * @param FirstName is the first name of the customer.
     * @param LastName is the last name of the customer.
     * @param PhoneNumber is the phone number of the customer.
     */
    public static void NewCostumer(String FirstName, String LastName, int PhoneNumber) {

    }

    /**
     * This method is used to add new appointments for a barber.
     * @param BarberPhoneNumber is the phone number of the barber.
     * @param date is the date of the appointment.
     * @param time is the time of the appointment.
     */
    public static void BarberNewAppointments(int BarberPhoneNumber, Date date, Time time) {

    }

    /**
     * This method is used for a customer to arrange an appointment with a barber.
     * @param CustomerPhoneNumber is the phone number of the customer.
     * @param BarberPhoneNumber is the phone number of the barber.
     * @param date is the date of the appointment.
     * @param time is the time of the appointment.
     * @param HairStyleName is the name of the hairstyle the customer wants.
     */
    public static void CustomerArrangeAppointment(int CustomerPhoneNumber, int BarberPhoneNumber, Date date, Time time, String HairStyleName) {

    }

    /**
     * This method is used to add a new hairstyle to the database.
     * @param HairStyleName is the name of the new hairstyle.
     * @param Price is the price of the new hairstyle.
     * @param ImageFile is an image of the new hairstyle.
     * @param BarberPhoneNumber is the phone number of the barber who can do this hairstyle.
     */
    public static void NewHairStyle(String HairStyleName, double Price, File ImageFile, int BarberPhoneNumber) {

    }

    /**
     * This method is used for a customer to rate a barber.
     * @param Rating is the rating given by the customer.
     * @param BarberPhone is the phone number of the barber.
     * @param CostumerPhone is the phone number of the customer.
     */
    public static void CostumerRating(double Rating, int BarberPhone, int CostumerPhone) {

    }

    /**
     * This method is used to update the rating of a barber.
     * @param NewRating is the new rating of the barber.
     * @param BarberPhone is the phone number of the barber.
     */
    private static void UpdateRating(double NewRating, int BarberPhone) {

    }

    /**
     * This method is used to get a list of all barbers in the database.
     */
    public static <Barber> void ListOfBarbers(){

    }

    /**
     * This method is used to get a list of all customers in the database.
     */
    public static <Costumer> void ListOfCostumer(){

    }
}

package com.example.barberbrisk.model;

import android.util.Log;

import com.example.barberbrisk.DB.DataBase;
import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.objects.HairCut;

public class AddHaircutModel {

    public AddHaircutModel()
    {
    }

    public void addHaircut(String haircutName, double price, String barber) {
        // Add haircut to the DB
        HairCut haircut = new HairCut(price, haircutName);
        //barber.addHaircut(haircut);
//        Log.d("AddHaircutModel", "addHaircut: " + haircut.toString() + " to " + barber.getUid());
        DataBase.AddNewHairStyle(haircut, barber);

    }
}

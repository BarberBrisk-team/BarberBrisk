package com.example.barberbrisk.model;

import com.example.barberbrisk.objects.Barber;
import com.example.barberbrisk.objects.HairCut;

public class AddHaircutModel {

    public AddHaircutModel()
    {
    }

    public void addHaircut(String haircutName, double price, Barber barber) {
        // Add haircut to the DB
        HairCut haircut = new HairCut(price, haircutName);
        barber.addHaircut(haircut);

    }
}

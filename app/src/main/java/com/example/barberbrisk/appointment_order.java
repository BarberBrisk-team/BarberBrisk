package com.example.barberbrisk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class appointment_order extends AppCompatActivity {

    List<String> Barber_list = new ArrayList<>();
    List<String> Treatment_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_order);

        //get the spinner from the xml.
        Spinner Barber_list_dropdown = findViewById(R.id.Barbers_spinner);
        Spinner Treatment_list_dropdown = findViewById(R.id.treatments_spinner);
        //create a list of items for the spinner.
        Barber_list.addAll(Arrays.asList(new String[]{"Sami Abed", "Natasha Aminov"}));
        //Todo: change the treatments list by the name choosen
        Treatment_list.addAll(Arrays.asList(new String[]{}));
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> Barber_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Barber_list);
        ArrayAdapter<String> Treat_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Treatment_list);
        //set the spinners adapter to the previously created one.
        Barber_list_dropdown.setAdapter(Barber_adapter);
        Treatment_list_dropdown.setAdapter(Treat_adapter);




        Barber_list_dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                System.out.println(item.toString());
                String barber_name = item.toString();
                update_treatment_list(barber_name);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    void update_treatment_list(String Barber_name)
    {
        Treatment_list.clear();
        //Todo: this is temparery, but it siulate choosing between diffrent hairdresser styles
        // it will be changed once we connect the database
        if(Barber_name == "Sami Abed")
        {
            Treatment_list.addAll(Arrays.asList(new String[]{"Abed list", "long with lifa"}));
        }
        else {
            Treatment_list.addAll(Arrays.asList(new String[]{"Natasha list", "long with lifa"}));
        }
        ArrayAdapter<String> Treat_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Treatment_list);

    }
}
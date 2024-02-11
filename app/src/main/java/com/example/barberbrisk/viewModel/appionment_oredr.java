package com.example.barberbrisk.viewModel;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;


public class appionment_oredr extends AppCompatActivity {
    private Button dateButton;
    private Button timeButton;
//    private Intent myIntent
//    private ArrayList<Barber>;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appionment_oredr);
        dateButton = findViewById(R.id.btnShowDataPicker);
        timeButton = findViewById(R.id.btnShowTimePicker);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(appionment_oredr.this);
                builder.setTitle("Pick a Date");
                View datePickerView = View.inflate(appionment_oredr.this, R.layout.date_picker, null);
                builder.setView(datePickerView).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatePicker datePicker = datePickerView.findViewById(R.id.datePicker);
                        Toast.makeText(appionment_oredr.this,
                                String.valueOf(datePicker.getMonth())+"/"+
                                String.valueOf(datePicker.getDayOfMonth())+"/"+
                                String.valueOf(datePicker.getYear()), Toast.LENGTH_LONG).show();

                    }
                }).setNeutralButton("Cancel", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(appionment_oredr.this);
                builder.setTitle("Pick a Time");
                View timePickerView = View.inflate(appionment_oredr.this, R.layout.time_picker, null);
                builder.setView(timePickerView).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TimePicker timePicker = timePickerView.findViewById(R.id.timePicker);
                        Toast.makeText(appionment_oredr.this,
                                String.valueOf(timePicker.getCurrentHour())+":"+
                                        String.valueOf(timePicker.getCurrentMinute())+"/"
                                , Toast.LENGTH_LONG).show();
                    }
                }).setNeutralButton("Cancel", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

}
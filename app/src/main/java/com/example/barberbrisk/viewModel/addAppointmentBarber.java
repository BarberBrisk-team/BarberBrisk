package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barberbrisk.R;
import com.example.barberbrisk.objects.Barber;

import java.util.Calendar;

public class addAppointmentBarber extends AppCompatActivity{
    private Button dateButton;
    private Spinner daySpinner;
    private Spinner monthSpinner;
    private Spinner yearSpinner;

    private Intent myIntent;

    private Barber barber;

    Integer[] daysArray = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 23, 24, 25, 26, 27, 28, 29, 30};
    String[] monthsArray = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

    Integer[] yearArray = generateYearsArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment_barber);
        dateButton = findViewById(R.id.buttonSelectDate);
        daySpinner = findViewById(R.id.dayDateSpinner);
        monthSpinner = findViewById(R.id.monthDateSpinner);
        yearSpinner = findViewById(R.id.yearDateSpinner);
        myIntent = getIntent();
        barber = myIntent.getParcelableExtra("BarBerObject");
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daySpinner.setVisibility(View.VISIBLE);
                loadDayIntoSpinner();
                loadMonthIntoSpinner();
                loadYearIntoSpinner();

            }
        });


    }

    public static Integer[] generateYearsArray() {
        // Get the current year.
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        // Create an array to hold the years.
        Integer[] years = new Integer[201]; // 200 years back + the current year

        // Fill the array with years.
        for (int i = 0; i < years.length; i++) {
            years[i] = currentYear - i;
        }

        return years;
    }


    private void loadDayIntoSpinner(){
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, daysArray){

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return super.getView(position, convertView, parent);
//                ((TextView) view.findViewById(android.R.id.text1)).setText();
//                return view;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return super.getDropDownView(position, convertView, parent);
//                ((TextView) view.findViewById(android.R.id.text1)).setText(barberList.get(position).getName());
//                return view;
            }
        };

    }

    private void loadMonthIntoSpinner(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, monthsArray){

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return super.getView(position, convertView, parent);
//                ((TextView) view.findViewById(android.R.id.text1)).setText();
//                return view;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return super.getDropDownView(position, convertView, parent);
//                ((TextView) view.findViewById(android.R.id.text1)).setText(barberList.get(position).getName());
//                return view;
            }
        };

    }

    private void loadYearIntoSpinner(){
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, yearArray){

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return super.getView(position, convertView, parent);
//                ((TextView) view.findViewById(android.R.id.text1)).setText();
//                return view;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return super.getDropDownView(position, convertView, parent);
//                ((TextView) view.findViewById(android.R.id.text1)).setText(barberList.get(position).getName());
//                return view;
            }
        };

    }
}
package com.example.barberbrisk.viewModel;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.barberbrisk.R;
import com.example.barberbrisk.model.AddApoBarberModel;
import java.sql.Timestamp;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;;
import java.util.Date;
import java.util.Locale;

public class AddAppointmentBarber extends AppCompatActivity {
    private AddApoBarberModel model;
    private int selectedYear, selectedMonth, selectedDay, selectedHour, selectedMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.model = new AddApoBarberModel(this);
        setContentView(R.layout.activity_add_appointment_barber);
        Button buttonSelectDate = findViewById(R.id.buttonSelectDate);
        Button buttonSelectTime = findViewById(R.id.buttonSelectTime);
        Intent myIntent = getIntent();
        String barberUid = myIntent.getStringExtra("Uid");
        //        we need to fill all the object fields by the DB
        if (barberUid != null) {
            model.getBarber(barberUid); // get the barber from the DB
        }
        buttonSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(view);
            } // show the date picker
        });

        buttonSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker(view);
            }
        });

        Button submitButton = findViewById(R.id.submitButtonAppointmentBarber);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date selectedDateTime = createSelectedDateTime();
                if (selectedDateTime != null) {
                    model.addAppointment(selectedDateTime); // add the appointment to the barber and update the DB
                    Toast.makeText(AddAppointmentBarber.this, "Appointment added successfully", Toast.LENGTH_SHORT).show();
                    // Go back to the previous activity
                    finish();
                }
            }
        });
    }
    /**
     * Show the date picker
     * @param view
     */

    public void showDatePicker(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Store the selected date
                        selectedYear = year;
                        selectedMonth = monthOfYear;
                        selectedDay = dayOfMonth;

                        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        Toast.makeText(AddAppointmentBarber.this, "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    /**
     * Show the time picker
     * @param view
     */
    public void showTimePicker(View view) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Store the selected time
                        selectedHour = hourOfDay;
                        selectedMinute = minute;

                        String selectedTime = hourOfDay + ":" + minute;
                        Toast.makeText(AddAppointmentBarber.this, "Selected Time: " + selectedTime, Toast.LENGTH_SHORT).show();
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }

    /**
     * Create a timestamp from the selected date and time
     * @return
     */
    private Timestamp createSelectedDateTime() {
        try {
            String selectedDateTimeString = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay + " " + selectedHour + ":" + selectedMinute;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
            Date parsedDate = dateFormat.parse(selectedDateTimeString);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }


    }
}





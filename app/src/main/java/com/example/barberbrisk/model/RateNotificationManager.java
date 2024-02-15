package com.example.barberbrisk.model;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.barberbrisk.DB.DataBase;
import com.example.barberbrisk.R;
import com.example.barberbrisk.objects.Appointment;
import com.example.barberbrisk.tests.TestActivity;
import com.example.barberbrisk.viewModel.RattingPage;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RateNotificationManager {

    public void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Notification";
            String description = "Channel for My Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("My Notification", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private PendingIntent getPendingIntent(Context context, String barberID, String clientID) {
        // Create an Intent that starts the activity
        Intent intent = new Intent(context, RattingPage.class);

        intent.putExtra("barberID", barberID);
        intent.putExtra("clientID", clientID);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Wrap the Intent with a PendingIntent
        int flags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;

        return PendingIntent.getActivity(context, 0, intent, flags);
    }

    private NotificationCompat.Builder getNotificationBuilder(Context context, PendingIntent pendingIntent) {
    // Create a notification builder object
    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "My Notification");

    // Set the notification content
    builder.setContentTitle("How was your haircut?");
    builder.setContentText("click here to rate your barber");
    builder.setPriority(NotificationCompat.PRIORITY_HIGH);
    builder.setSmallIcon(com.google.android.gms.base.R.drawable.common_google_signin_btn_text_light);

    // Set the PendingIntent as the content intent of the notification
    builder.setContentIntent(pendingIntent);
    builder.setAutoCancel(true); // Automatically removes the notification when the user taps it

    return builder;
}

private void sendRateNotification(Context context, String barberID, String clientID) {
    Log.d("RateNotificationManager", "Sending notification");
    createNotificationChannel(context);
    PendingIntent pendingIntent = getPendingIntent(context, barberID, clientID);
    NotificationCompat.Builder builder = getNotificationBuilder(context, pendingIntent);

    // Create a notification manager object
    NotificationManagerCompat manager = NotificationManagerCompat.from(context);

    // Display the notification
    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
        // TODO: Consider calling ActivityCompat#requestPermissions here
        return;
    }
    manager.notify(1, builder.build());
}

    /***
     * This method is used to schedule a notification for the user to rate the barber.
     * use it like this:
     * Appointment ap = new Appointment("dUILdwEj8tgGuP0y4W4GYP9XkvU2", "2024-02-15 18:47:00", true);
     *  rateNotificationManager.scheduleNotification(getApplicationContext(), "dUILdwEj8tgGuP0y4W4GYP9XkvU2", "SnBHUa3stOglf5QaR8hQZfnjrRU2",ap );
     * @param context
     * @param barberID
     * @param clientID
     * @param appointment
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void scheduleNotification(Context context, String barberID, String clientID, Appointment appointment) {


       Log.d("RateNotificationManager", "Scheduling notification for " + appointment.getTimeAndDate().toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        //Todo: get the date and time from the appointment
        String dateTimeStr =appointment.getTimeAndDate().toString(); //"2024-02-15 19:24";//appointment.getTimeAndDate().toString();
        dateTimeStr = dateTimeStr.substring(0, 16);
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
        LocalDateTime now = LocalDateTime.now();

        long delay = Duration.between(now, dateTime).toMillis();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            sendRateNotification(context, barberID, clientID);
        }, delay, TimeUnit.MILLISECONDS);
    }
}

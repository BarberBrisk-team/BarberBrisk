package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barberbrisk.DB.DataBase;
import com.example.barberbrisk.R;

public class RattingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ratting_page);
        TextView textView = findViewById(R.id.BarberName);
        Button sendRatingButton = findViewById(R.id.send_ratting);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        final float[] rating = {ratingBar.getRating()};

        Intent intent = getIntent();
        String barberID = intent.getStringExtra("barberID");
        String clientID = intent.getStringExtra("clientID");
        String appointmentID = intent.getStringExtra("appointmentID");


        textView.setText(DataBase.getBaraberlist().get(barberID).getName());


        // Set a listener that gets called when the rating changes
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float r, boolean fromUser) {
                // The rating parameter contains the new rating
                // Save the rating here

                Log.d("Rating", "Rating changed to: " + r);
                rating[0] = ratingBar.getRating();
            }
        });


        sendRatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Rating", "Rating sent: " + rating[0]);
            }
        });
    }


}
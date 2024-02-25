package com.example.barberbrisk.viewModel;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barberbrisk.R;
import com.example.barberbrisk.model.AddHaircutModel;

public class AddHaircutPage extends AppCompatActivity {
    /**
     * This function is called when the activity is created.
     * It gets the barber details from the DB and sets the data on the activity.
     * @param savedInstanceState - the saved instance state
     */
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_haircut_page);
        Button submitButton = findViewById(R.id.add_haircut_Submit_btn);
        EditText priceEditText = findViewById(R.id.Price_editText);
        EditText HaircutNameEditText = findViewById(R.id.name_editText);
        Intent intent = getIntent();
        String barberUid = intent.getStringExtra("barber");

        Log.d("AddHaircutPage", "onCreate: " + barberUid);
//        Log.d("AddHaircutPage", "onCreate: " + barber.getUid());

        submitButton.setOnClickListener(view -> {
            String price = priceEditText.getText().toString();
            String haircutName = HaircutNameEditText.getText().toString();
            if (price.isEmpty() || haircutName.isEmpty()) {
                Toast.makeText(AddHaircutPage.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }
            //Todo: Add the haircut to the DB
            AddHaircutModel addHaircutModel = new AddHaircutModel();

                  addHaircutModel.addHaircut(haircutName, Double.parseDouble(price), barberUid);

            finish();
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
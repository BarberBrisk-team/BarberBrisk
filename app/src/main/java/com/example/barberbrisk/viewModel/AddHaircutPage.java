package com.example.barberbrisk.viewModel;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barberbrisk.DB.DataBase;
import com.example.barberbrisk.R;

public class AddHaircutPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_haircut_page);
        Button submitButton = findViewById(R.id.add_haircut_Submit_btn);
        EditText priceEditText = findViewById(R.id.Price_editText);
        EditText HaircutNameEditText = findViewById(R.id.name_editText);
        submitButton.setOnClickListener(view -> {
            String price = priceEditText.getText().toString();
            String haircutName = HaircutNameEditText.getText().toString();
            if (price.isEmpty() || haircutName.isEmpty()) {
                Toast.makeText(AddHaircutPage.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }
            //Todo: Add the haircut to the DB


            finish();
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
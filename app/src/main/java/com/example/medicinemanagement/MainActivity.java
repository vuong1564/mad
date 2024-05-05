package com.example.medicinemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicinemanagement.medicine.MedicineActivity;
import com.example.medicinemanagement.usesofmedicine.UsesOfMedicineActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button medicineButton = findViewById(R.id.medicine);
        medicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MedicineActivity.class);
                startActivity(intent);
            }
        });

        Button usesOfMedicineButton = findViewById(R.id.uses_of_medicine);
        usesOfMedicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UsesOfMedicineActivity.class);
                startActivity(intent);
            }
        });
    }
}
package com.example.medicinemanagement.usesofmedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicinemanagement.R;

public class CreateUsesOfMedicine extends AppCompatActivity {

    EditText editTextNameUsesOfMedicine, editTextDescriptionUsesOfMedicine;
    Button createUsesOfMedicineButton;
    UsesOfMedicineDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_uses_of_medicine);

        editTextNameUsesOfMedicine = findViewById(R.id.editTextNameUsesOfMedicine);
        editTextDescriptionUsesOfMedicine = findViewById(R.id.editTextDescriptionUsesOfMedicine);
        createUsesOfMedicineButton = findViewById(R.id.create_uses_of_medicine);
        databaseHelper = new UsesOfMedicineDatabaseHelper(this);

        createUsesOfMedicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextNameUsesOfMedicine.getText().toString().trim();
                String description = editTextDescriptionUsesOfMedicine.getText().toString().trim();

                if (name.isEmpty() || description.isEmpty()) {
                    Toast.makeText(CreateUsesOfMedicine.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    databaseHelper.insertUsesOfMedicine(name, description);
                    Toast.makeText(CreateUsesOfMedicine.this, "Uses of Medicine added", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
package com.example.medicinemanagement.usesofmedicine;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.medicinemanagement.R;

public class CreateUsesOfMedicine extends AppCompatActivity {

    private EditText editTextNameUsesOfMedicine;
    private EditText editTextDescriptionUsesOfMedicine;
    private UsesOfMedicineDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_uses_of_medicine);

        editTextNameUsesOfMedicine = findViewById(R.id.editTextNameUsesOfMedicine);
        editTextDescriptionUsesOfMedicine = findViewById(R.id.editTextDescriptionUsesOfMedicine);
        Button createUsesOfMedicineButton = findViewById(R.id.create_uses_of_medicine);
        databaseHelper = new UsesOfMedicineDatabaseHelper(this);

        createUsesOfMedicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextNameUsesOfMedicine.getText().toString();
                String description = editTextDescriptionUsesOfMedicine.getText().toString();

                if (name.isEmpty() || description.isEmpty()) {
                    Toast.makeText(CreateUsesOfMedicine.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Insert new UsesOfMedicine into the database
                    // Note: You need to implement the insertUsesOfMedicine method in your UsesOfMedicineDatabaseHelper class
                    databaseHelper.insertUsesOfMedicine(name, description);
                    Toast.makeText(CreateUsesOfMedicine.this, "Uses of Medicine added", Toast.LENGTH_SHORT).show();

                    // Close this activity and return to UsesOfMedicineActivity
                    finish();
                }
            }
        });
    }
}
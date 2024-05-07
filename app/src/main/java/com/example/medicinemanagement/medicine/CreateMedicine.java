package com.example.medicinemanagement.medicine;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicinemanagement.usesofmedicine.UsesOfMedicineDatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import com.example.medicinemanagement.R;

public class CreateMedicine extends AppCompatActivity {

    EditText medicineName, medicinePrice;
    Spinner medicineForm, medicineUsage;
    Button addButton;
    MedicineDatabaseHelper db;
    UsesOfMedicineDatabaseHelper usesDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_medicine);

        medicineName = findViewById(R.id.editTextMedicineName);
        medicinePrice = findViewById(R.id.editTextMedicinePrice);
        medicineForm = findViewById(R.id.spinnerMedicineForm);
        medicineUsage = findViewById(R.id.spinnerMedicineUsage);
        addButton = findViewById(R.id.buttonAddMedicine);

        db = new MedicineDatabaseHelper(this);
        usesDb = new UsesOfMedicineDatabaseHelper(this);

        // Populate the form spinner
        ArrayAdapter<CharSequence> formAdapter = ArrayAdapter.createFromResource(this, R.array.medicine_form_array, android.R.layout.simple_spinner_item);
        formAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicineForm.setAdapter(formAdapter);

        // Populate the usage spinner
        Cursor cursor = usesDb.getAllUsesOfMedicine();
        List<String> usageList = new ArrayList<>();
        while (cursor.moveToNext()) {
            usageList.add(cursor.getString(1)); // Assuming the name is at index 1
        }
        ArrayAdapter<String> usageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, usageList);
        usageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicineUsage.setAdapter(usageAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = medicineName.getText().toString().trim();
                String price = medicinePrice.getText().toString().trim();
                String form = medicineForm.getSelectedItem().toString().trim();
                String usage = medicineUsage.getSelectedItem().toString().trim();

                if (name.isEmpty() || price.isEmpty() || form.isEmpty() || usage.isEmpty()) {
                    Toast.makeText(CreateMedicine.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    db.insertMedicine(name, price, form, usage);
                    Toast.makeText(CreateMedicine.this, "Medicine added", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
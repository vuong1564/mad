package com.example.medicinemanagement.medicine;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicinemanagement.R;
import com.example.medicinemanagement.usesofmedicine.UsesOfMedicineDatabaseHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UpdateMedicine extends AppCompatActivity {

    EditText medicineName, medicinePrice;
    Spinner medicineForm, medicineUsage;
    Button updateButton, deleteButton;

    String id, name, form, price, usage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_medicine);

        medicineName = findViewById(R.id.editTextNameMedicine);
        medicinePrice = findViewById(R.id.editTextPriceMedicine);
        medicineForm = findViewById(R.id.spinnerFormMedicine);
        medicineUsage = findViewById(R.id.spinnerUsageMedicine);
        updateButton = findViewById(R.id.update_button_Medicine);
        deleteButton = findViewById(R.id.delete_button_Medicine);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedicineDatabaseHelper databaseHelper = new MedicineDatabaseHelper(UpdateMedicine.this);
                name = medicineName.getText().toString().trim();
                price = medicinePrice.getText().toString().trim();
                form = medicineForm.getSelectedItem().toString().trim();
                usage = medicineUsage.getSelectedItem().toString().trim();
                databaseHelper.updateMedicine(id, name, price, form, usage);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

void getAndSetIntentData() {
    if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("price") && getIntent().hasExtra("form") && getIntent().hasExtra("usage")) {
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        price = getIntent().getStringExtra("price");
        form = getIntent().getStringExtra("form");
        usage = getIntent().getStringExtra("usage");

        medicineName.setText(name);
        medicinePrice.setText(String.valueOf(price));

        // Set the selected item of the spinners based on the value
        String[] formArray = getResources().getStringArray(R.array.medicine_form_array);
        List<String> formList = new ArrayList<>(Arrays.asList(formArray));
        formList = sortListWithSelectedFirst(formList, form);
        ArrayAdapter<String> formAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, formList);
        formAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicineForm.setAdapter(formAdapter);

        // Populate the usage spinner
        UsesOfMedicineDatabaseHelper usesDb = new UsesOfMedicineDatabaseHelper(this);
        Cursor cursor = usesDb.getAllUsesOfMedicine();
        List<String> usageList = new ArrayList<>();
        while (cursor.moveToNext()) {
            usageList.add(cursor.getString(1)); // Assuming the name is at index 1
        }
        usageList = sortListWithSelectedFirst(usageList, usage);
        ArrayAdapter<String> usageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, usageList);
        usageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicineUsage.setAdapter(usageAdapter);

    } else {
        Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
    }
}

List<String> sortListWithSelectedFirst(List<String> list, String selected) {
    list.remove(selected);
    Collections.sort(list);
    list.add(0, selected);
    return list;
}

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?")
               .setMessage("Are you sure you want to delete " + name + " ?")
               .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       // Delete medicine data from the database
                          MedicineDatabaseHelper databaseHelper = new MedicineDatabaseHelper(UpdateMedicine.this);
                            databaseHelper.deleteMedicine(id);
                       finish();
                   }
               })
               .setNegativeButton("No", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                   }
               })
               .create()
               .show();
    }
}
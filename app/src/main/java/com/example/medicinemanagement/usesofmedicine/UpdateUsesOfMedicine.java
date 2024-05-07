package com.example.medicinemanagement.usesofmedicine;

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

public class UpdateUsesOfMedicine extends AppCompatActivity {

    EditText editTextNameUsesOfMedicine, editTextDescriptionUsesOfMedicine;
    Button updateButton, deleteButton;

    String id, name, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_uses_of_medicine);

        editTextNameUsesOfMedicine = findViewById(R.id.editTextNameUsesOfMedicine2);
        editTextDescriptionUsesOfMedicine = findViewById(R.id.editTextDescriptionUsesOfMedicine2);
        updateButton = findViewById(R.id.update_button_UsesOfMedicine);
        deleteButton = findViewById(R.id.delete_button_UsesOfMedicine);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsesOfMedicineDatabaseHelper databaseHelper = new UsesOfMedicineDatabaseHelper(UpdateUsesOfMedicine.this);
                name = editTextNameUsesOfMedicine.getText().toString().trim();
                description = editTextDescriptionUsesOfMedicine.getText().toString().trim();
                databaseHelper.updateUsesOfMedicine(id, name, description);
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
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("description")) {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            description = getIntent().getStringExtra("description");

            editTextNameUsesOfMedicine.setText(name);
            editTextDescriptionUsesOfMedicine.setText(description);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                UsesOfMedicineDatabaseHelper databaseHelper = new UsesOfMedicineDatabaseHelper(UpdateUsesOfMedicine.this);
                databaseHelper.deleteUsesOfMedicine(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
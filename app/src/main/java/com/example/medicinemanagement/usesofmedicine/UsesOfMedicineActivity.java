package com.example.medicinemanagement.usesofmedicine;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medicinemanagement.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class UsesOfMedicineActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;

    UsesOfMedicineDatabaseHelper myDB;
    ArrayList<String> medicineId, medicineName, medicineDescription;
    UsesOfMedicineAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uses_of_medicine);

        recyclerView = findViewById(R.id.recyclerViewUsesOfMedicine);
        add_button = findViewById(R.id.floatingActionButton);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsesOfMedicineActivity.this, CreateUsesOfMedicine.class);
                startActivity(intent);
            }
        });

        myDB = new UsesOfMedicineDatabaseHelper(UsesOfMedicineActivity.this);
        medicineId = new ArrayList<>();
        medicineName = new ArrayList<>();
        medicineDescription = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new UsesOfMedicineAdapter(UsesOfMedicineActivity.this, this, medicineId, medicineName, medicineDescription);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(UsesOfMedicineActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.getAllUsesOfMedicine();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                medicineId.add(cursor.getString(0));
                medicineName.add(cursor.getString(1));
                medicineDescription.add(cursor.getString(2));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                UsesOfMedicineDatabaseHelper myDB = new UsesOfMedicineDatabaseHelper(UsesOfMedicineActivity.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(UsesOfMedicineActivity.this, UsesOfMedicineActivity.class);
                startActivity(intent);
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
package com.example.medicinemanagement.medicine;

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

public class MedicineActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;

    MedicineDatabaseHelper myDB;
    ArrayList<String> medicineId, medicineName, medicinePrice, medicineForm, medicineUsage;
    MedicineAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        recyclerView = findViewById(R.id.recyclerViewMedicine);
        add_button = findViewById(R.id.floatingActionButtonMedicine);
        empty_imageview = findViewById(R.id.empty_imageviewMedicine);
        no_data = findViewById(R.id.no_dataMedicine);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicineActivity.this, CreateMedicine.class);
                startActivity(intent);
            }
        });

        myDB = new MedicineDatabaseHelper(MedicineActivity.this);
        medicineId = new ArrayList<>();
        medicineName = new ArrayList<>();
        medicinePrice = new ArrayList<>();
        medicineForm = new ArrayList<>();
        medicineUsage = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new MedicineAdapter(MedicineActivity.this, this, medicineId, medicineName, medicinePrice, medicineForm, medicineUsage);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MedicineActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.getAllMedicine();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                medicineId.add(cursor.getString(0));
                medicineName.add(cursor.getString(1));
                medicinePrice.add(cursor.getString(2));
                medicineForm.add(cursor.getString(3));
                medicineUsage.add(cursor.getString(4));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        medicineId.clear();
        medicineName.clear();
        medicinePrice.clear();
        medicineForm.clear();
        medicineUsage.clear();
        storeDataInArrays();
        customAdapter.notifyDataSetChanged();
    }
}
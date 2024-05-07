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
    ArrayList<String> UsesOfmedicineId, UsesOfmedicineName, medicineDescription;
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
        UsesOfmedicineId = new ArrayList<>();
        UsesOfmedicineName = new ArrayList<>();
        medicineDescription = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new UsesOfMedicineAdapter(UsesOfMedicineActivity.this, this, UsesOfmedicineId, UsesOfmedicineName, medicineDescription);
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
                UsesOfmedicineId.add(cursor.getString(0));
                UsesOfmedicineName.add(cursor.getString(1));
                medicineDescription.add(cursor.getString(2));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }


    @Override
    protected void onResume() {
    super.onResume();
    // This is where you can place the code that needs to be executed when the activity is resumed
    // For example, you can refresh your data here
    UsesOfmedicineId.clear();
    UsesOfmedicineName.clear();
    medicineDescription.clear();
    storeDataInArrays();
    customAdapter.notifyDataSetChanged();
}
}
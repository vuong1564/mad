package com.example.medicinemanagement.usesofmedicine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.medicinemanagement.R;
import java.util.List;

public class UsesOfMedicineActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uses_of_medicine);

        recyclerView = findViewById(R.id.recyclerViewUsesOfMedicine);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsesOfMedicineActivity.this, CreateUsesOfMedicine.class);
                startActivity(intent);
            }
        });

        // Set up the RecyclerView to display the list of uses of medicine
        UsesOfMedicineDatabaseHelper databaseHelper = new UsesOfMedicineDatabaseHelper(this);
        List<UsesOfMedicineModel> usesOfMedicineList = databaseHelper.getAllUsesOfMedicine();

        UsesOfMedicineAdapter adapter = new UsesOfMedicineAdapter(usesOfMedicineList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Fetch the data from the database
        UsesOfMedicineDatabaseHelper databaseHelper = new UsesOfMedicineDatabaseHelper(this);
        List<UsesOfMedicineModel> usesOfMedicineList = databaseHelper.getAllUsesOfMedicine();

        // Update the RecyclerView
        UsesOfMedicineAdapter adapter = new UsesOfMedicineAdapter(usesOfMedicineList);
        recyclerView.setAdapter(adapter);
    }
}
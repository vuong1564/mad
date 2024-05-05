package com.example.medicinemanagement.medicine;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medicinemanagement.R;

public class MedicineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        // Enable the back button in the ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        // This method is called when the up button is pressed. Just finish the activity.
        finish();
        return true;
    }
}

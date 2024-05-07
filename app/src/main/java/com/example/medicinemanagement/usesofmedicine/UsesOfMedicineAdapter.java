package com.example.medicinemanagement.usesofmedicine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicinemanagement.R;

import java.util.ArrayList;

public class UsesOfMedicineAdapter extends RecyclerView.Adapter<UsesOfMedicineAdapter.ViewHolder> {

    private final ArrayList<String> medicineId;
    private final ArrayList<String> medicineName;
    private final ArrayList<String> medicineDescription;
    private final Context context;
    private final Activity activity;

    public UsesOfMedicineAdapter(Activity activity, Context context, ArrayList<String> medicineId, ArrayList<String> medicineName,
                                 ArrayList<String> medicineDescription) {
        this.context = context;
        this.activity = activity;
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.medicineDescription = medicineDescription;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.medicineIdTxt.setText(medicineId.get(position));
        holder.medicineNameTxt.setText(medicineName.get(position));
        holder.medicineDescriptionTxt.setText(medicineDescription.get(position));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateUsesOfMedicine.class);
            intent.putExtra("id", medicineId.get(position));
            intent.putExtra("name", medicineName.get(position));
            intent.putExtra("description", medicineDescription.get(position));
            activity.startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return medicineId.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView medicineIdTxt, medicineNameTxt, medicineDescriptionTxt;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            medicineIdTxt = itemView.findViewById(R.id.UsesOfMedicine_id_txt);
            medicineNameTxt = itemView.findViewById(R.id.UsesOfMedicine_name_txt);
            medicineDescriptionTxt = itemView.findViewById(R.id.UsesOfMedicine_description_txt);
        }
    }
}
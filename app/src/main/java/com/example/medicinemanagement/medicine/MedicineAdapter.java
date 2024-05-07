package com.example.medicinemanagement.medicine;

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

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ViewHolder> {

    private final ArrayList<String> medicineId;
    private final ArrayList<String> medicineName;
    private final ArrayList<String> medicinePrice;
    private final ArrayList<String> medicineForm;
    private final ArrayList<String> medicineUsage;
    private final Context context;
    private final Activity activity;

    public MedicineAdapter(Activity activity, Context context, ArrayList<String> medicineId, ArrayList<String> medicineName, ArrayList<String> medicinePrice, ArrayList<String> medicineForm, ArrayList<String> medicineUsage) {
        this.context = context;
        this.activity = activity;
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.medicinePrice = medicinePrice;
        this.medicineForm = medicineForm;
        this.medicineUsage = medicineUsage;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.medicineIdTxt.setText(medicineId.get(position));
        holder.medicineNameTxt.setText(medicineName.get(position));
        holder.medicinePriceTxt.setText(medicinePrice.get(position));
        holder.medicineFormTxt.setText(medicineForm.get(position));
        holder.medicineUsageTxt.setText(medicineUsage.get(position));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateMedicine.class);
            intent.putExtra("id", medicineId.get(position));
            intent.putExtra("name", medicineName.get(position));
            intent.putExtra("price", medicinePrice.get(position));
            intent.putExtra("form", medicineForm.get(position));
            intent.putExtra("usage", medicineUsage.get(position));
            activity.startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return medicineId.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView medicineIdTxt, medicineNameTxt, medicinePriceTxt, medicineFormTxt, medicineUsageTxt;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            medicineIdTxt = itemView.findViewById(R.id.medicine_id_txt);
            medicineNameTxt = itemView.findViewById(R.id.medicine_name_txt);
            medicinePriceTxt = itemView.findViewById(R.id.medicine_price_txt);
            medicineFormTxt = itemView.findViewById(R.id.medicine_form_txt);
            medicineUsageTxt = itemView.findViewById(R.id.medicine_usage_txt);
        }
    }
}
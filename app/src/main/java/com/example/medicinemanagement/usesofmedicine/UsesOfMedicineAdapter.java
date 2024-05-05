package com.example.medicinemanagement.usesofmedicine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.recyclerview.widget.RecyclerView;
import com.example.medicinemanagement.R;
import java.util.List;

public class UsesOfMedicineAdapter extends RecyclerView.Adapter<UsesOfMedicineAdapter.ViewHolder> {

    private List<UsesOfMedicineModel> usesOfMedicineList;

    public UsesOfMedicineAdapter(List<UsesOfMedicineModel> usesOfMedicineList) {
        this.usesOfMedicineList = usesOfMedicineList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.uses_of_medicine_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UsesOfMedicineModel usesOfMedicine = usesOfMedicineList.get(position);
        holder.nameButton.setText(usesOfMedicine.getName());
    }

    @Override
    public int getItemCount() {
        return usesOfMedicineList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button nameButton;

        public ViewHolder(View view) {
            super(view);
            nameButton = view.findViewById(R.id.nameButton);
        }
    }
}
package com.example.medicinemanagement.usesofmedicine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicinemanagement.R;

import java.util.ArrayList;

public class UsesOfMedicineAdapter extends RecyclerView.Adapter<UsesOfMedicineAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList<String> medicineId, medicineName, medicineDescription;

    UsesOfMedicineAdapter(Activity activity, Context context, ArrayList<String> medicineId, ArrayList<String> medicineName,
                          ArrayList<String> medicineDescription){
        this.activity = activity;
        this.context = context;
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.medicineDescription = medicineDescription;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.medicineIdTxt.setText(String.valueOf(medicineId.get(position)));
        holder.medicineNameTxt.setText(String.valueOf(medicineName.get(position)));
        holder.medicineDescriptionTxt.setText(String.valueOf(medicineDescription.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateUsesOfMedicine.class);
                intent.putExtra("id", String.valueOf(medicineId.get(position)));
                intent.putExtra("name", String.valueOf(medicineName.get(position)));
                intent.putExtra("description", String.valueOf(medicineDescription.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return medicineId.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView medicineIdTxt, medicineNameTxt, medicineDescriptionTxt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            medicineIdTxt = itemView.findViewById(R.id.UsesOfMedicine_id_txt);
            medicineNameTxt = itemView.findViewById(R.id.UsesOfMedicine_name_txt);
            medicineDescriptionTxt = itemView.findViewById(R.id.UsesOfMedicine_description_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
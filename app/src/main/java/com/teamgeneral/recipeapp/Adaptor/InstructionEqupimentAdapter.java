package com.teamgeneral.recipeapp.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teamgeneral.recipeapp.Models.Equipment;
import com.teamgeneral.recipeapp.Models.Ingredient;
import com.teamgeneral.recipeapp.R;

import java.util.List;

public class InstructionEqupimentAdapter extends RecyclerView.Adapter<InstructionEqupimentsViewHolder>{
  Context context;
  List<Equipment> list;

    public InstructionEqupimentAdapter(Context context, List<Equipment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionEqupimentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new InstructionEqupimentsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_step_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionEqupimentsViewHolder holder, int position) {
     holder.tv_item.setText(list.get(position).name);
     holder.tv_item.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/equipment_100x100/"+list.get(position).image).into(holder.iv_item);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
class InstructionEqupimentsViewHolder extends RecyclerView.ViewHolder{
ImageView iv_item;
TextView tv_item;
    public InstructionEqupimentsViewHolder(@NonNull View itemView) {
        super(itemView);
        iv_item=itemView.findViewById(R.id.iv_item);
        tv_item=itemView.findViewById(R.id.tv_item);
    }
}

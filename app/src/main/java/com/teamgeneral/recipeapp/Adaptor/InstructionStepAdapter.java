package com.teamgeneral.recipeapp.Adaptor;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teamgeneral.recipeapp.Models.Step;
import com.teamgeneral.recipeapp.R;

import java.util.List;

public class InstructionStepAdapter extends RecyclerView.Adapter<StepViewHolder>{
    Context context;
    List<Step> list;

    public InstructionStepAdapter(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StepViewHolder(LayoutInflater.from(context).inflate(R.layout.list_steps,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        holder.tv_steps_number.setText(String.valueOf(list.get(position).number));
        holder.tv_steps_title.setText(list.get(position).step);
        holder.rv_instruction_ingredients.setHasFixedSize(true);
        holder.rv_instruction_ingredients.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        InstructionIngredientsAdapter instructionIngredientsAdapter=new InstructionIngredientsAdapter(context,list.get(position).ingredients);
        holder.rv_instruction_ingredients.setAdapter(instructionIngredientsAdapter);
        holder.rv_equipments.setHasFixedSize(true);
        holder.rv_equipments.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        InstructionEqupimentAdapter instructionEqupimentAdapter=new InstructionEqupimentAdapter(context,list.get(position).equipment);
        holder.rv_equipments.setAdapter(instructionEqupimentAdapter);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class StepViewHolder extends RecyclerView.ViewHolder{
TextView tv_steps_number,tv_steps_title;
RecyclerView rv_equipments,rv_instruction_ingredients;
    public StepViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_steps_number=itemView.findViewById(R.id.tv_steps_number);
        tv_steps_title=itemView.findViewById(R.id.tv_steps_title);
        rv_equipments=itemView.findViewById(R.id.rv_equipments);
        rv_instruction_ingredients=itemView.findViewById(R.id.rv_instruction_ingredients);
    }
}

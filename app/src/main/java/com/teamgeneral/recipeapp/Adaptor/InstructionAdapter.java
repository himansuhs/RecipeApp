package com.teamgeneral.recipeapp.Adaptor;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teamgeneral.recipeapp.Models.InstructionResponse;
import com.teamgeneral.recipeapp.R;

import java.util.List;

public class InstructionAdapter extends RecyclerView.Adapter<InstructionViewHolder>{
    Context context;
    List<InstructionResponse> list;

    public InstructionAdapter(Context context, List<InstructionResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionViewHolder holder, int position) {
        holder.tv_instruction.setText(list.get(position).name);
       holder.rv_instructions.setHasFixedSize(true);
       holder.rv_instructions.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
       InstructionStepAdapter instructionStepAdapter=new InstructionStepAdapter(context,list.get(position).steps);
       holder.rv_instructions.setAdapter(instructionStepAdapter);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionViewHolder extends RecyclerView.ViewHolder {
    TextView tv_instruction;
    RecyclerView rv_instructions;
    public InstructionViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_instruction=itemView.findViewById(R.id.tv_instruction);
        rv_instructions=itemView.findViewById(R.id.rv_instructions);
    }
}

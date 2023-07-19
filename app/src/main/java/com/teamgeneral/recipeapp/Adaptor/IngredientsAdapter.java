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
import com.teamgeneral.recipeapp.Models.ExtendedIngredient;
import com.teamgeneral.recipeapp.R;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder> {
    Context context;
    List<ExtendedIngredient> list;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_ingredients,parent,false));


    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.tv_ingredients_name.setText(list.get(position).name);
        holder.tv_ingredients_name.setSelected(true);
        holder.tv_quantity.setText(list.get(position).original);
        holder.tv_quantity.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.iv_ingredients);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class IngredientsViewHolder extends RecyclerView.ViewHolder{
 TextView tv_quantity,tv_ingredients_name;
 ImageView iv_ingredients;
    public IngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_quantity=(TextView) itemView.findViewById(R.id.tv_quantity);
        tv_ingredients_name=(TextView) itemView.findViewById(R.id.tv_ingredients_name);
        iv_ingredients= itemView.findViewById(R.id.iv_ingredients);
    }
}

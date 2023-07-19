package com.teamgeneral.recipeapp.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teamgeneral.recipeapp.Listeners.RecipeClickListener;
import com.teamgeneral.recipeapp.Models.Recipe;
import com.teamgeneral.recipeapp.R;

import org.w3c.dom.Text;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder> {
    Context context;
    List<Recipe> list;
    RecipeClickListener recipeClickListener;

    public RandomRecipeAdapter(Context context, List<Recipe> list,RecipeClickListener recipeClickListener) {
        this.context = context;
        this.list = list;
        this.recipeClickListener=recipeClickListener;
    }


    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.text_view_title.setText(list.get(position).title);
        holder.tv_like.setText(list.get(position).aggregateLikes+"likes");
        holder.tv_person.setText(list.get(position).servings+"Servings");
        holder.tv_time.setText(list.get(position).readyInMinutes+" Minutes");
        Picasso.get().load(list.get(position).image).into(holder.image_view_food);
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeClickListener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class RandomRecipeViewHolder extends RecyclerView.ViewHolder{
    CardView card_view;
    TextView tv_person,tv_time,tv_like,text_view_title;
    ImageView image_view_food;


    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        card_view=(CardView) itemView.findViewById(R.id.card_view);
        tv_person=(TextView) itemView.findViewById(R.id.tv_person);
        tv_time=(TextView) itemView.findViewById(R.id.tv_time);
        tv_like=(TextView) itemView.findViewById(R.id.tv_like);
        image_view_food=(ImageView) itemView.findViewById(R.id.image_view_food);
        text_view_title=(TextView) itemView.findViewById(R.id.text_view_title);

    }
}

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
import com.teamgeneral.recipeapp.Listeners.SimilarListner;
import com.teamgeneral.recipeapp.Models.SimilarRecipeModel;
import com.teamgeneral.recipeapp.R;

import java.util.List;


public class SimilarAdapter extends RecyclerView.Adapter<SimilarViewHolder> {
    Context context;
    List<SimilarRecipeModel> list;
    RecipeClickListener similarListner;

    public SimilarAdapter(Context context, List<SimilarRecipeModel> list, RecipeClickListener similarListner) {
        this.context = context;
        this.list = list;
        this.similarListner=similarListner;
    }

    @NonNull
    @Override
    public SimilarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimilarViewHolder(LayoutInflater.from(context).inflate(R.layout.list_similar,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarViewHolder holder, int position) {
        holder.tv_title.setText(list.get(position).title);
        holder.tv_title.setSelected(true);
        holder.tv_serving.setText(list.get(position).servings+"person");
        Picasso.get().load("https://spoonacular.com/recipeImages/"+list.get(position).id+"-556x370."+list.get(position).imageType).into(holder.iv_similar);
        holder.similar_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                similarListner.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));



            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class SimilarViewHolder extends RecyclerView.ViewHolder{
CardView similar_holder;
TextView tv_title,tv_serving;
ImageView iv_similar;
    public SimilarViewHolder(@NonNull View itemView) {
        super(itemView);
        similar_holder=itemView.findViewById(R.id.similar_holder);
        tv_title=itemView.findViewById(R.id.tv_title);
        tv_serving=itemView.findViewById(R.id.tv_serving);
        iv_similar=itemView.findViewById(R.id.iv_similar);
    }
}

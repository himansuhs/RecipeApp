package com.teamgeneral.recipeapp.Adaptor;



import static com.teamgeneral.recipeapp.R.array.tags;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamgeneral.recipeapp.R;

import java.util.ArrayList;
import java.util.List;

public class Categories extends RecyclerView.Adapter<CatViewHolder> {
    Context context;
    //ArrayList<> strings;
    List<String> tags=new ArrayList<>();

    public Categories(Context context, List<String> tags) {
        this.context = context;
        this.tags = tags;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CatViewHolder(LayoutInflater.from(context).inflate(R.layout.main_course,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
class CatViewHolder extends RecyclerView.ViewHolder{
TextView tv_name;
ImageView image_view;
    public CatViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_name=itemView.findViewById(R.id.tv_name);
        image_view=itemView.findViewById(R.id.image_view);
    }
}

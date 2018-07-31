package com.example.android.bakingapp.userInterface;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Recipe;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesListAdapter extends RecyclerView.Adapter<RecipesListAdapter.RecipeViewHolder> {

    private Recipe[] mRecipesList;
    private OnClickListener onClickListener;

    public RecipesListAdapter(OnClickListener onClickListener)
    {
        this.onClickListener = onClickListener;
    }

    interface OnClickListener{
        void onItemClick(Recipe recipe);
    }

    public void setRecipeList(Recipe[] mRecipesList) {
        this.mRecipesList = mRecipesList;
        notifyDataSetChanged();
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater =  LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_recipe,parent,false);

        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecipeViewHolder holder, int position) {
        holder.bind(position);

        final String imagePath = mRecipesList[position].getImage();

        if (!TextUtils.isEmpty(imagePath)) {
            Picasso.get()
                    .load(imagePath)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(holder.recipeImage, new Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError(Exception e) {
                            Picasso.get()
                                    .load(imagePath)
                                    .error(R.mipmap.food_placeholder)
                                    .into(holder.recipeImage);
                        }
                    });
        } else {
            holder.recipeImage.setImageResource(R.mipmap.food_placeholder);
        }
    }

    @Override
    public int getItemCount() {
        if(mRecipesList==null)
            return 0;
        else return mRecipesList.length;
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.recipe_name)
        TextView txtRecipe;
        @BindView(R.id.recipe_image)
        ImageView recipeImage;

        RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        void bind(int position)
        {
            txtRecipe.setText(mRecipesList[position].getName());
        }

        @Override
        public void onClick(View view) {
            onClickListener.onItemClick(mRecipesList[getAdapterPosition()]);
        }
    }
}

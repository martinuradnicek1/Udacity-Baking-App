package com.example.android.bakingapp.userInterface;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.data.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.bakingapp.userInterface.RecipesList.EXTRA_RECIPE;

public class StepsListFragment extends Fragment {

    Intent intent;
    Context context;
    Step[] mStepsList;
    Recipe mRecipe;
    RecipeDetailsAdapter mStepAdapter;
    @BindView(R.id.recycler_view_steps)
    RecyclerView mRecyclerViewSteps;
    @BindView(R.id.txt_ingredients)
    TextView txtIngredients;
    @BindView(R.id.scroll_view)
    NestedScrollView mScrollView;

    public StepsListFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.intent = this.getActivity().getIntent();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity.getApplicationContext();
        this.intent = activity.getIntent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_steps_list,container,false);

        ButterKnife.bind(this,view);

        mStepAdapter = new RecipeDetailsAdapter((RecipeDetailsAdapter.OnClickListener)getActivity());
        mRecyclerViewSteps.setAdapter(mStepAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        mRecyclerViewSteps.setLayoutManager(layoutManager);
        mRecyclerViewSteps.setHasFixedSize(true);
        mRecyclerViewSteps.setNestedScrollingEnabled(false);

        if(intent.getExtras()!=null) {
            mRecipe = intent.getExtras().getParcelable(EXTRA_RECIPE);
            if(mRecipe!=null) {

                mStepsList = mRecipe.getStepList();
                mStepAdapter.setStepList(mStepsList);

                txtIngredients.setText(Ingredient.getStringListIngredients(mRecipe.getIngredientList(),context));
            }
        }

        return view;
    }
}

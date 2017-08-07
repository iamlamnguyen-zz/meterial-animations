package com.example.lamng.material_animations.features;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;

import com.example.lamng.material_animations.R;
import com.example.lamng.material_animations.adapter.SampleMenuAdapter;
import com.example.lamng.material_animations.model.SampleData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements SampleMenuAdapter.OnItemClickListener {

    List<SampleData> sampleDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupMenu();
        setupRecyclerView();
        setupWindowAnimations();
    }

    @SuppressWarnings("ResourceType")
    private void setupMenu() {
        sampleDataList = new ArrayList<>();
        sampleDataList.add(new SampleData(1, ContextCompat.getColor(this, R.color.red), "Transitions"));
        sampleDataList.add(new SampleData(2, ContextCompat.getColor(this, R.color.blue), "Shared Elements"));
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvAnimationView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SampleMenuAdapter sampleMenuAdapter = new SampleMenuAdapter(sampleDataList);
        sampleMenuAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(sampleMenuAdapter);
    }

    @Override
    public void onSampleDataSelectedClick(SampleMenuAdapter.SamplesViewHolder holder, SampleData sampleData) {
        Intent intent;
        switch (sampleData.getId()) {
            case 1:
                intent = new Intent(this, TransitionActivity.class);
                intent.putExtra(EXTRA_DATA, sampleData);
                transitionTo(intent);
                break;
            case 2:
                transitionToActivity(SharedElementActivity.class, holder, sampleData);
                break;
            default:
                break;
        }
    }

    @Override
    protected void setupWindowAnimations() {
        super.setupWindowAnimations();
        // Re-enter transition is executed when returning to this activity
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.START);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);
    }
}

package com.example.lamng.material_animations.features;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.widget.TextView;

import com.example.lamng.material_animations.R;
import com.example.lamng.material_animations.model.SampleData;

public class SharedElementActivity extends AppCompatActivity {

    private static final String EXTRA_DATA = "sample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element);
        SampleData sampleData = (SampleData) getIntent().getExtras().getSerializable(EXTRA_DATA);

        setupWindowAnimations();
        setupLayout(sampleData);

        if (sampleData == null) {
            return;
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);

        TextView tvTitle = toolbar.findViewById(R.id.tvTitle);
        tvTitle.setText(sampleData.getName());
        tvTitle.setTextColor(ContextCompat.getColor(this, android.R.color.black));
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        tvTitle.setTransitionName(getResources().getString(R.string.transition_name_title_shared));
    }

    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        getWindow().getEnterTransition().setDuration(getResources().getInteger(R.integer.anim_duration_long));
    }

    private void setupLayout(SampleData sample) {
        FirstSharedElementFragment firstSharedElementFragment = FirstSharedElementFragment.newInstance(sample);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Transition for fragment
            Slide slideTransition = new Slide(Gravity.START);
            slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
            // Create fragment and define some of it transitions
            firstSharedElementFragment.setReenterTransition(slideTransition);
            firstSharedElementFragment.setExitTransition(slideTransition);
            firstSharedElementFragment.setSharedElementEnterTransition(new ChangeBounds());
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_content, firstSharedElementFragment)
                .commit();
    }

}

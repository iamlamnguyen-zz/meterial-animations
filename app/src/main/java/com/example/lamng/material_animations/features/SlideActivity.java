package com.example.lamng.material_animations.features;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.Visibility;
import android.view.Gravity;
import android.widget.ImageView;

import com.example.lamng.material_animations.R;
import com.example.lamng.material_animations.model.SampleData;

public class SlideActivity extends BaseActivity {

    private int type;
    private SampleData sampleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        bindData();
        setupWindowAnimations();
    }

    private void bindData() {
        type = getIntent().getExtras().getInt(EXTRA_TYPE);
        sampleData = (SampleData) getIntent().getExtras().getSerializable(EXTRA_DATA);
        if (sampleData == null) {
            return;
        }
        ImageView ivCircle = (ImageView) findViewById(R.id.ivCircle);
        DrawableCompat.setTint(ivCircle.getDrawable(), sampleData.getColor());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void setupWindowAnimations() {
        super.setupWindowAnimations();
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        Transition transition;
        if (type == TYPE_PROGRAMMATICALLY) {
            transition = buildEnterTransition();
        }  else {
            transition = TransitionInflater.from(this).inflateTransition(R.transition.slide_from_bottom);
        }

        if (transition == null) {
            return;
        }
        getWindow().setEnterTransition(transition);
    }

    private Visibility buildEnterTransition() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            return null;
        }
        Slide enterTransition = new Slide();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        enterTransition.setSlideEdge(Gravity.START);
        return enterTransition;
    }
}

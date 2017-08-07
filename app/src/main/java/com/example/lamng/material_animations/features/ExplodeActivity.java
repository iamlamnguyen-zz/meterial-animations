package com.example.lamng.material_animations.features;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.widget.ImageView;

import com.example.lamng.material_animations.R;
import com.example.lamng.material_animations.model.SampleData;

public class ExplodeActivity extends BaseActivity {

    private int type;
    private SampleData sampleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explode);
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
            transition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
        }

        if (transition == null) {
            return;
        }
        getWindow().setEnterTransition(transition);
    }

    private Transition buildEnterTransition() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            return null;
        }
        Explode enterTransition = new Explode();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        enterTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                Log.i(ExplodeActivity.class.getSimpleName(), "onTransitionStart");
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Log.i(ExplodeActivity.class.getSimpleName(), "onTransitionEnd");

            }

            @Override
            public void onTransitionCancel(Transition transition) {
                Log.i(ExplodeActivity.class.getSimpleName(), "onTransitionCancel");

            }

            @Override
            public void onTransitionPause(Transition transition) {
                Log.i(ExplodeActivity.class.getSimpleName(), "onTransitionPause");

            }

            @Override
            public void onTransitionResume(Transition transition) {
                Log.i(ExplodeActivity.class.getSimpleName(), "onTransitionResume");

            }
        });
        return enterTransition;
    }


}

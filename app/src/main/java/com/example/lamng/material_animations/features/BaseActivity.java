package com.example.lamng.material_animations.features;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;

import com.example.lamng.material_animations.R;
import com.example.lamng.material_animations.adapter.SampleMenuAdapter;
import com.example.lamng.material_animations.model.SampleData;
import com.example.lamng.material_animations.utils.TransitionHelper;

/**
 * Created by Lam Nguyen on 6/21/17.
 * Siclo Mobile company.
 */

public class BaseActivity extends AppCompatActivity {

    static final int TYPE_XML = 1;
    static final int TYPE_PROGRAMMATICALLY = 0;

    static final String EXTRA_TYPE = "type";
    static final String EXTRA_DATA = "sample";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    protected void transitionToActivity(Class target, SampleMenuAdapter.SamplesViewHolder viewHolder, SampleData sampleData) {
        final Pair[] pairs = TransitionHelper.createSafeTransitionParticipants(this, false,
                new Pair<>(viewHolder.ivSampleIcon, getResources().getString(R.string.transition_name_icon_shared)),
                new Pair<>(viewHolder.tvSampleTitle, getResources().getString(R.string.transition_name_title_shared)));
        this.startActivity(target, pairs, sampleData);
    }

    private void startActivity(Class target, Pair<View, String>[] pairs, SampleData sample) {
        Intent i = new Intent(this, target);
        // makeSceneTransitionAnimation() method to define shared element origin view and transition name.
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        i.putExtra("sample", sample);
        startActivity(i, transitionActivityOptions.toBundle());
    }

    @SuppressWarnings("unchecked")
    protected void transitionTo(Intent i) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, true);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        startActivity(i, transitionActivityOptions.toBundle());
    }

    protected void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fade);
        getWindow().setEnterTransition(transition);
        getWindow().setReenterTransition(transition);
        getWindow().setExitTransition(transition);

        // Return Transition
//        Slide slide = new Slide();
//        slide.setDuration(1000);
//        slide.setSlideEdge(Gravity.START);
//        getWindow().setReturnTransition(slide);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        finishAfterTransition();
    }
}

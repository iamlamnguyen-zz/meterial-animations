package com.example.lamng.material_animations.features;

import android.content.Intent;
import android.os.Bundle;

import com.example.lamng.material_animations.R;
import com.example.lamng.material_animations.model.SampleData;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransitionActivity extends BaseActivity {

    private SampleData sampleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        ButterKnife.bind(this);

        bindData();
        setupWindowAnimations();
    }

    private void bindData() {
        sampleData = (SampleData) getIntent().getExtras().getSerializable(EXTRA_DATA);
    }

    @OnClick(R.id.btExplodeCode)
    void onClickWithAnimExplodeByCode() {
        Intent i = new Intent(this, ExplodeActivity.class);
        i.putExtra(EXTRA_DATA, sampleData);
        i.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
        transitionTo(i);
    }

    @OnClick(R.id.btExplodeXML)
    void onClickWithAnimExplodeByXML() {
        Intent i = new Intent(this, ExplodeActivity.class);
        i.putExtra(EXTRA_DATA, sampleData);
        i.putExtra(EXTRA_TYPE, TYPE_XML);
        transitionTo(i);
    }

    @OnClick(R.id.btSlideCode)
    void onClickWithAnimSlideByCode() {
        Intent i = new Intent(this, SlideActivity.class);
        i.putExtra(EXTRA_DATA, sampleData);
        i.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
        transitionTo(i);
    }

    @OnClick(R.id.btSlideXML)
    void onClickWithAnimSlideByXML() {
        Intent i = new Intent(this, SlideActivity.class);
        i.putExtra(EXTRA_DATA, sampleData);
        i.putExtra(EXTRA_TYPE, TYPE_XML);
        transitionTo(i);
    }

    @OnClick(R.id.btExit)
    void onClickExitWithNonAnim() {
        finish();
    }

    @OnClick(R.id.btExitTransition)
    void onClickExitWithAnim() {
        onBackPressed();
    }
}

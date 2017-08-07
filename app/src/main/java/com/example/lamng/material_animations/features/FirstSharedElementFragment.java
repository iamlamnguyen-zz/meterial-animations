package com.example.lamng.material_animations.features;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lamng.material_animations.R;
import com.example.lamng.material_animations.model.SampleData;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstSharedElementFragment extends Fragment {

    private SampleData sampleData;
    private ImageView ivCircle;

    private static final String EXTRA_DATA = "sample";

    public static FirstSharedElementFragment newInstance(SampleData sampleData) {
        Bundle args = new Bundle();

        args.putSerializable(EXTRA_DATA, sampleData);
        FirstSharedElementFragment fragment = new FirstSharedElementFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shared_element, container, false);
        ButterKnife.bind(this, view);

        sampleData = (SampleData) getArguments().getSerializable(EXTRA_DATA);
        if (sampleData == null) {
            return view;
        }
        ivCircle = view.findViewById(R.id.ivCircle);
        DrawableCompat.setTint(ivCircle.getDrawable(), sampleData.getColor());

        return view;
    }

    @OnClick(R.id.btNextWithoutOverlap)
    void onClickButtonNextWithAnimOverlap() {
        addNextFragment(sampleData, ivCircle, false);
    }

    @OnClick(R.id.btNextWithOverlap)
    void onClickButtonNextWithoutAnimOverlap() {
        addNextFragment(sampleData, ivCircle, true);
    }

    private void addNextFragment(SampleData sampleData, ImageView ivCircle, boolean overlap) {
        SecondSharedElementFragment secondSharedElementFragment = SecondSharedElementFragment.newInstance(sampleData);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide slideTransition = new Slide(Gravity.END);
            slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));

            ChangeBounds changeBoundsTransition = new ChangeBounds();
            changeBoundsTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));

            secondSharedElementFragment.setEnterTransition(slideTransition);
            secondSharedElementFragment.setAllowEnterTransitionOverlap(overlap);
            secondSharedElementFragment.setAllowReturnTransitionOverlap(overlap);
            secondSharedElementFragment.setSharedElementEnterTransition(changeBoundsTransition);
        }
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_content, secondSharedElementFragment)
                .addToBackStack(null)
                .addSharedElement(ivCircle, "Circle Shared Element")
                .commit();
    }
}

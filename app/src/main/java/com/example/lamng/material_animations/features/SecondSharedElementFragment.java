package com.example.lamng.material_animations.features;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lamng.material_animations.R;
import com.example.lamng.material_animations.model.SampleData;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondSharedElementFragment extends Fragment {

    private static final String EXTRA_DATA = "sample";

    public static SecondSharedElementFragment newInstance(SampleData sampleData) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATA, sampleData);
        SecondSharedElementFragment fragment = new SecondSharedElementFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second_shared_element, container, false);
        SampleData sampleData = (SampleData) getArguments().getSerializable(EXTRA_DATA);
        if (sampleData == null) {
            return view;
        }
        ImageView squareBlue = view.findViewById(R.id.ivCircle);
        DrawableCompat.setTint(squareBlue.getDrawable(), sampleData.getColor());

        return view;
    }

}

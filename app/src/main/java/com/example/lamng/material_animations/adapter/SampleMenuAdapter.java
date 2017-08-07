package com.example.lamng.material_animations.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lamng.material_animations.R;
import com.example.lamng.material_animations.model.SampleData;

import java.util.List;

/**
 * Created by Lam Nguyen on 6/21/17.
 * Siclo Mobile company.
 */

public class SampleMenuAdapter extends RecyclerView.Adapter<SampleMenuAdapter.SamplesViewHolder> {
    public interface OnItemClickListener {
        void onSampleDataSelectedClick(SamplesViewHolder holder, SampleData itemSampleData);
    }
    private OnItemClickListener itemClickListener;

    private final List<SampleData> sampleDataList;

    public SampleMenuAdapter(List<SampleData> sampleDataList) {
        this.sampleDataList = sampleDataList;
    }

    @Override
    public SamplesViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sample_data, parent, false);
        return new SamplesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SamplesViewHolder holder, final int position) {
        final SampleData sampleData = sampleDataList.get(position);

        holder.ivSampleIcon.setColorFilter(sampleData.getColor());
        holder.tvSampleTitle.setText(sampleData.getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.onSampleDataSelectedClick(holder, sampleData);
                }
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return sampleDataList.size();
    }

    public class SamplesViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public ImageView ivSampleIcon;
        public TextView tvSampleTitle;

        SamplesViewHolder(View rootView) {
            super(rootView);
            view = rootView;
            ivSampleIcon = rootView.findViewById(R.id.ivSampleIcon);
            tvSampleTitle = rootView.findViewById(R.id.tvSampleTitle);
        }
    }
}


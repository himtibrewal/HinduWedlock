package com.colaborotech.thehinduwedlock.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Himanshu on 9/16/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ProfileHolder> {

    private List recycleViewModelList;
    private Context context;
    private int layout;
    ReturnView returnView;
    int from;

    public interface ReturnView {
        void getAdapterView(View view, List objects, int position, int from);
    }

    public RecyclerAdapter(List recycleViewModelList, Context context, int layout, ReturnView returnView, int from) {
        this.recycleViewModelList = recycleViewModelList;
        this.context = context;
        this.layout = layout;
        this.returnView = returnView;
        this.from = from;
    }

    @Override
    public int getItemCount() {
        return recycleViewModelList.size();
    }

    @Override
    public void onBindViewHolder(ProfileHolder rideHistoryViewHolder, final int i) {
        returnView.getAdapterView(rideHistoryViewHolder.v, recycleViewModelList, i, from);
    }

    @Override
    public ProfileHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(layout, viewGroup, false);

        return new ProfileHolder(itemView);
    }

    public static class ProfileHolder extends RecyclerView.ViewHolder {
        View v;

        public ProfileHolder(View v) {
            super(v);
            this.v = v;

        }
    }
}
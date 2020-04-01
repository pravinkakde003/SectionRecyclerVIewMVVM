package com.user.indexedrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.CallSuper;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.user.indexedrecyclerview.databinding.RecyclerViewDataBinding;

import java.util.List;

public abstract class BaseMovieAdapter extends SectionedRecyclerViewAdapter<BaseMovieAdapter.SubheaderHolder,
        BaseMovieAdapter.MovieViewHolder> {

    public interface OnItemClickListener {
        void onItemClicked(RecyclerViewDataModel movie);
    }

    List<RecyclerViewDataModel> movieList;

    OnItemClickListener onItemClickListener;

    static class SubheaderHolder extends RecyclerView.ViewHolder {
        TextView mSubheaderText;

        SubheaderHolder(View itemView) {
            super(itemView);
            this.mSubheaderText = itemView.findViewById(R.id.textViewSectionHeader);
        }
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        RecyclerViewDataBinding recyclerViewDataBinding;
        MovieViewHolder(RecyclerViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.recyclerViewDataBinding = viewDataBinding;
        }
    }

    BaseMovieAdapter(List<RecyclerViewDataModel> itemList) {
        super();
        this.movieList = itemList;
    }

    @Override
    public MovieViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewDataBinding recyclerViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recyclerview_item_layout, parent, false);


        return new MovieViewHolder(recyclerViewDataBinding);
    }

    @Override
    public SubheaderHolder onCreateSubheaderViewHolder(ViewGroup parent, int viewType) {
        return new SubheaderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.section_item, parent, false));
    }

    @Override
    @CallSuper
    public void onBindSubheaderViewHolder(SubheaderHolder subheaderHolder, int nextItemPosition) {
    }

    @Override
    public int getItemSize() {
        return movieList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

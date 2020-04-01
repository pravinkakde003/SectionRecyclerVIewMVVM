package com.user.indexedrecyclerview;

import java.util.List;


public class MovieAdapterByTitle extends BaseMovieAdapter {

    public MovieAdapterByTitle(List<RecyclerViewDataModel> itemList) {
        super(itemList);
    }

    @Override
    public boolean onPlaceSubheaderBetweenItems(int position) {
        final char movieTitleFirstCharacter = movieList.get(position).getTitle().charAt(0);
        final char nextMovieTitleFirstCharacter = movieList.get(position + 1).getTitle().charAt(0);
        return movieTitleFirstCharacter != nextMovieTitleFirstCharacter;
    }

    @Override
    public void onBindItemViewHolder(final MovieViewHolder holder, final int position) {
        final RecyclerViewDataModel movie = movieList.get(position);
        holder.recyclerViewDataBinding.setRecyclerViewData(movie);
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClicked(movie));
    }

    @Override
    public void onBindSubheaderViewHolder(SubheaderHolder subheaderHolder, int nextItemPosition) {
        super.onBindSubheaderViewHolder(subheaderHolder, nextItemPosition);
        final RecyclerViewDataModel nextMovie = movieList.get(nextItemPosition);
        subheaderHolder.mSubheaderText.setText(nextMovie.getTitle().substring(0, 1));
    }
}

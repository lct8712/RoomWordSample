package com.chentian.roomwordsample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chentian.roomwordsample.R;
import com.chentian.roomwordsample.model.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chentian
 */
public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private List<Word> wordList;

    public WordListAdapter() {
        wordList = new ArrayList<>();
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        holder.bindData(wordList.get(position).getWord());
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public void setData(List<Word> wordList) {
        this.wordList = wordList;
        notifyDataSetChanged();
    }

    static class WordViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtWord;

        private WordViewHolder(View itemView) {
            super(itemView);

            txtWord = itemView.findViewById(R.id.txt_word);
        }

        private void bindData(String word) {
            txtWord.setText(word);
        }
    }

}

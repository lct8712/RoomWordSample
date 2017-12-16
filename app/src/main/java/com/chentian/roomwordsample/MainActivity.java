package com.chentian.roomwordsample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.chentian.roomwordsample.adapter.WordListAdapter;
import com.chentian.roomwordsample.model.Word;
import com.chentian.roomwordsample.viewmodel.WordViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WordListAdapter wordListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(this.<Toolbar>findViewById(R.id.toolbar));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddWordActivity.class));
            }
        });

        wordListAdapter = new WordListAdapter();
        RecyclerView listWord = findViewById(R.id.list_view_word);
        listWord.setAdapter(wordListAdapter);
        listWord.setLayoutManager(new LinearLayoutManager(this));

        WordViewModel wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        wordViewModel.getWordListLiveData().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable List<Word> words) {
                wordListAdapter.setData(words);
                Log.d("chentian", "live data on changed: " + words.size());
            }
        });
    }
}

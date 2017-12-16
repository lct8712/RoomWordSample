package com.chentian.roomwordsample;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chentian.roomwordsample.model.Word;
import com.chentian.roomwordsample.viewmodel.WordViewModel;

public class AddWordActivity extends AppCompatActivity {

    private WordViewModel wordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        final EditText editWord = findViewById(R.id.edit_word);
        Button btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = editWord.getText().toString();
                if (TextUtils.isEmpty(word)) {
                    Toast.makeText(AddWordActivity.this, "Input a word", Toast.LENGTH_SHORT).show();
                    return;
                }

                wordViewModel.insert(new Word(word));
                finish();
            }
        });
    }
}

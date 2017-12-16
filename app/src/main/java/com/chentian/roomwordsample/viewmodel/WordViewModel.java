package com.chentian.roomwordsample.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.chentian.roomwordsample.model.Word;
import com.chentian.roomwordsample.room.WordRepository;

import java.util.List;

/**
 * @author chentian
 */
public class WordViewModel extends AndroidViewModel {

    private final WordRepository wordRepository;

    private LiveData<List<Word>> wordListLiveData;

    public WordViewModel(@NonNull Application application) {
        super(application);

        wordRepository = new WordRepository(application);
        wordListLiveData = wordRepository.getWordListLiveData();
    }

    public void insert(Word word) {
        wordRepository.insert(word);
    }

    public LiveData<List<Word>> getWordListLiveData() {
        return wordListLiveData;
    }
}

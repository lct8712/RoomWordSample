package com.chentian.roomwordsample.room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.chentian.roomwordsample.model.Word;

import java.util.List;

/**
 * @author chentian
 */
public class WordRepository {

    private final WordDao wordDao;

    private LiveData<List<Word>> wordListLiveData;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getInstance(application);
        wordDao = db.wordDao();
        wordListLiveData = wordDao.getAllWords();
    }

    public void insert(Word word) {
        new InsertASyncTask(wordDao).execute(word);
        Log.d("chentian", "insert new word: " + word.getWord());
    }

    public LiveData<List<Word>> getWordListLiveData() {
        return wordListLiveData;
    }

    private static class InsertASyncTask extends AsyncTask<Word, Void, Void> {

        private final WordDao wordDao;

        private InsertASyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insert(words[0]);
            return null;
        }
    }
}

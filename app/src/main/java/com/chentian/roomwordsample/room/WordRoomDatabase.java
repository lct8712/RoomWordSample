package com.chentian.roomwordsample.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.chentian.roomwordsample.model.Word;

/**
 * @author chentian
 */
@Database(entities = Word.class, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();

    private static WordRoomDatabase instance;

    public static WordRoomDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (WordRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, WordRoomDatabase.class, "word_database")
                        .addCallback(new Callback() {
                            @Override
                            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                super.onOpen(db);
                                new InitASyncTask(instance).execute();
                                Log.d("chentian", "word db init");
                            }
                        })
                        .build();
                }
            }
        }
        return instance;
    }

    private static class InitASyncTask extends AsyncTask<Word, Void, Void> {

        private final WordDao wordDao;

        private InitASyncTask(WordRoomDatabase wordRoomDatabase) {
            this.wordDao = wordRoomDatabase.wordDao();
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteAll();
            wordDao.insert(new Word("apple"));
            wordDao.insert(new Word("orange"));
            return null;
        }
    }
}

package com.chentian.roomwordsample.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.chentian.roomwordsample.model.Word;

import java.util.List;

/**
 * @author chentian
 */
@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Query("delete from word_table")
    void deleteAll();

    @Query("select * from word_table order by word asc")
    LiveData<List<Word>> getAllWords();

}

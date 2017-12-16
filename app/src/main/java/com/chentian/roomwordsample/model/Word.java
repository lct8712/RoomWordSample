package com.chentian.roomwordsample.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * @author chentian
 */
@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    private String word;

    public Word(@NonNull String word) {
        this.word = word;
    }

    @NonNull
    public String getWord() {
        return word;
    }
}

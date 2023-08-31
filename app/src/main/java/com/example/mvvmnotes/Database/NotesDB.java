package com.example.mvvmnotes.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mvvmnotes.Model.Notes;
import com.example.mvvmnotes.Dao.NotesDao;

@Database(entities = {Notes.class}, version = 1)
public abstract class NotesDB extends RoomDatabase {

    public abstract NotesDao notesDao();

    public static NotesDB INSTANCE;

    public static NotesDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotesDB.class,
                            "NOTES DATABSE")
                    .build();
        }
        return INSTANCE;
    }
}

package com.example.mvvmnotes.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvmnotes.Model.Notes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("SELECT * FROM Notes")
    LiveData<List<Notes>> getAllNotes();

    @Query("SELECT * FROM Notes ORDER BY `Notes Priority` DESC")
    LiveData<List<Notes>> getAllNotesHighToLow();

    @Query("SELECT * FROM Notes ORDER BY `Notes Priority` ASC")
    LiveData<List<Notes>> getAllNotesLowToHigh();

    @Insert
    void insertNotes(Notes... notes);

    @Query("DELETE FROM Notes WHERE notesID=:id")
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes notes);
}

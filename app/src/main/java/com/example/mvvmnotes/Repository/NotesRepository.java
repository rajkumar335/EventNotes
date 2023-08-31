package com.example.mvvmnotes.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mvvmnotes.Dao.NotesDao;
import com.example.mvvmnotes.Database.NotesDB;
import com.example.mvvmnotes.Model.Notes;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotesRepository {

    public NotesDao notesDao;

    public LiveData<List<Notes>> allNotes;
    public LiveData<List<Notes>> allNotesHighToLow;
    public LiveData<List<Notes>> allNotesLowToHigh;

    public NotesDB notesDB;

    public NotesRepository(Application application) {
        notesDB = NotesDB.getInstance(application);
        notesDao = notesDB.notesDao();
        allNotes =  notesDao.getAllNotes();
        allNotesHighToLow = notesDao.getAllNotesHighToLow();
        allNotesLowToHigh = notesDao.getAllNotesLowToHigh();
    }

    public void insertNotes(Notes notes) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                notesDao.insertNotes(notes);
            }
        });
        service.shutdown();
    }

    public void deleteNotes(int id) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                notesDao.deleteNotes(id);
            }
        });
        service.shutdown();
    }

    public void updateNotes(Notes notes) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                notesDao.updateNotes(notes);
            }
        });
        service.shutdown();
    }
}

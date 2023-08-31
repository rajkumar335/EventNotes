package com.example.mvvmnotes.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmnotes.Model.Notes;
import com.example.mvvmnotes.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository notesRepository;
    public LiveData<List<Notes>> allNotes;
    public LiveData<List<Notes>> allNotesHighToLow;
    public LiveData<List<Notes>> allNotesLowToHigh;

    public NotesViewModel(@NonNull Application application) {
        super(application);

        notesRepository = new NotesRepository(application);
        allNotes = notesRepository.allNotes;
        allNotesHighToLow = notesRepository.allNotesHighToLow;
        allNotesLowToHigh = notesRepository.allNotesLowToHigh;
    }

    public void insertNotes(Notes notes) {
        notesRepository.insertNotes(notes);
    }

    public void updateNotes(Notes notes) {
        notesRepository.updateNotes(notes);
    }

    public void deleteNodes(int id) {
        notesRepository.deleteNotes(id);
    }
}

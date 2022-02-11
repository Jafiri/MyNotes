package com.example.mynotes.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mynotes.Models.Notes;
import com.example.mynotes.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository notesRepository;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> lowTohigh;
    public LiveData<List<Notes>> highTolow;

    public NotesViewModel(@NonNull Application application) {
        super(application);

        notesRepository = new NotesRepository(application);
        getAllNotes = notesRepository.getallNotes;
        lowTohigh = notesRepository.lowTohigh;
        highTolow = notesRepository.highTolow;
    }

    public void insertNote( Notes notes){
        notesRepository.insertNotes(notes);
    }

    public void deleteNote(int id){
        notesRepository.deleteNotes(id);
    }

    public void updateNote(Notes note){
        notesRepository.updateNotes(note);
    }


}

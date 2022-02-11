package com.example.mynotes.Repository;

import android.app.Activity;
import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mynotes.Dao.NotesDao;
import com.example.mynotes.Database.NotesDatabase;
import com.example.mynotes.Models.Notes;

import java.util.List;

public class NotesRepository {

    public NotesDao notesDao;
    public LiveData<List<Notes>> getallNotes;
    public LiveData<List<Notes>> lowTohigh;
    public LiveData<List<Notes>> highTolow;

    public NotesRepository(Application application) {
        NotesDatabase database = NotesDatabase.getNotesDatabaseInstance(application);
        notesDao = database.notesDao();
        getallNotes = notesDao.getallNoters();
        lowTohigh = notesDao.lowTohigh();
        highTolow = notesDao.highTolow();
    }

    public void insertNotes(Notes notes){
        notesDao.insertNotes(notes);
    }

    public void deleteNotes(int id){
        notesDao.deleteNotes(id);
    }

    public void updateNotes(Notes notes){
        notesDao.updateNotes(notes);
    }


}

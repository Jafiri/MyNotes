package com.example.mynotes.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mynotes.Models.Notes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    // Data Access Object

    @Query("SELECT * FROM Notes_Database")
    LiveData<List<Notes>> getallNoters();

    @Query("SELECT * FROM Notes_Database ORDER BY notes_priority DESC")
    LiveData<List<Notes>> highTolow();

    @Query("SELECT * FROM Notes_Database ORDER BY notes_priority ASC")
    LiveData<List<Notes>> lowTohigh();

    @Insert
    Void insertNotes(Notes... notes);

    @Query("DELETE FROM Notes_Database WHERE id =:id ")
    Void deleteNotes(int id);

    @Update
    void updateNotes(Notes notes);


}

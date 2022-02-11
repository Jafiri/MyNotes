package com.example.mynotes.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.mynotes.Models.Notes;
import com.example.mynotes.Dao.NotesDao;

@Database(entities = {Notes.class}, version = 1, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract NotesDao notesDao();

    public static NotesDatabase notesDatabaseInstance;

    public static NotesDatabase getNotesDatabaseInstance(Context context) {
        if (notesDatabaseInstance == null) {
            notesDatabaseInstance = Room.databaseBuilder(context.getApplicationContext()
                    , NotesDatabase.class,
                    "Notes_Database").allowMainThreadQueries().build();
        }
        return notesDatabaseInstance;
    }


}

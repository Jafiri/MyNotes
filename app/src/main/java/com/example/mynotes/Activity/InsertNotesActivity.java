package com.example.mynotes.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.example.mynotes.Models.Notes;
import com.example.mynotes.R;
import com.example.mynotes.ViewModel.NotesViewModel;
import com.example.mynotes.databinding.ActivityInsertnotesBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InsertNotesActivity extends AppCompatActivity {

    ActivityInsertnotesBinding binding;
    String title, subtitle, notes;
    String priority ="1";
    NotesViewModel notesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertnotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

            getSupportActionBar().setTitle("Add Notes");

        notesViewModel = ViewModelProviders.of(InsertNotesActivity.this).get(NotesViewModel.class);

        binding.greenpriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.greenpriority.setImageResource(R.drawable.done);
                binding.yellowpriority.setImageResource(0);
                binding.bluepriority.setImageResource(0);
                priority ="1";
            }
        });
        binding.yellowpriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priority ="2";
                binding.greenpriority.setImageResource(0);
                binding.yellowpriority.setImageResource(R.drawable.done);
                binding.bluepriority.setImageResource(0);
            }
        });
        binding.bluepriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priority ="3";
                binding.greenpriority.setImageResource(0);
                binding.yellowpriority.setImageResource(0);
                binding.bluepriority.setImageResource(R.drawable.done);
            }
        });


        binding.btnDoneNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = binding.txtTitle.getText().toString();
                subtitle = binding.txtSubtitle.getText().toString();
                notes = binding.txtNotes.getText().toString();

                if(notes.equals("")){
                    binding.txtNotes.setError("field cannot be empty");
                }else {
                    CreateNotes(title, subtitle, notes);
                }
            }
        });

    }

    private void CreateNotes(String title, String subtitle, String notes) {  //MMMM d, YYYY

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d, yyyy", date.getTime());
       // String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        Notes notes1 = new Notes();
        notes1.notesTitle=title;
        notes1.notesSubtitle=subtitle;
        notes1.notes=notes;
        notes1.notesDate = sequence.toString();
        notes1.notesPriority = priority;

        notesViewModel.insertNote(notes1);

        Toast.makeText(InsertNotesActivity.this, "Notes created successfully", Toast.LENGTH_SHORT).show();
        finish();

    }
}
package com.example.mynotes.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynotes.Models.Notes;
import com.example.mynotes.R;
import com.example.mynotes.ViewModel.NotesViewModel;
import com.example.mynotes.databinding.ActivityInsertnotesBinding;
import com.example.mynotes.databinding.ActivityUpdateNotesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {

    ActivityUpdateNotesBinding binding;
    String priority = "1";
    String stitle,ssubTitle,snotes,spriority;
    NotesViewModel notesViewModel;
    int iid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("Update Notes");

        notesViewModel = ViewModelProviders.of(UpdateNotesActivity.this).get(NotesViewModel.class);

        iid = getIntent().getIntExtra("id",0);
        stitle = getIntent().getStringExtra("title");
        ssubTitle = getIntent().getStringExtra("subtitle");
        snotes = getIntent().getStringExtra("notes");
        spriority = getIntent().getStringExtra("priority");

        binding.txtUptitle.setText(stitle);
        binding.txtUpsubtitle.setText(ssubTitle);
        binding.txtUpnotes.setText(snotes);

        if(spriority != null && spriority.equals("1")){
            binding.upgreenpriority.setImageResource(R.drawable.done);
        }else if(spriority != null && spriority.equals("2")){
            binding.upyellowpriority.setImageResource(R.drawable.done);
        }else if(spriority != null && spriority.equals("3")) {
            binding.upbluepriority.setImageResource(R.drawable.done);
        }
        binding.upgreenpriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.upgreenpriority.setImageResource(R.drawable.done);
                binding.upyellowpriority.setImageResource(0);
                binding.upbluepriority.setImageResource(0);
                priority ="1";
            }
        });
        binding.upyellowpriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priority ="2";
                binding.upgreenpriority.setImageResource(0);
                binding.upyellowpriority.setImageResource(R.drawable.done);
                binding.upbluepriority.setImageResource(0);
            }
        });
        binding.upbluepriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priority ="3";
                binding.upgreenpriority.setImageResource(0);
                binding.upyellowpriority.setImageResource(0);
                binding.upbluepriority.setImageResource(R.drawable.done);
            }
        });
        binding.btnUpdoneNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String title = binding.txtUptitle.getText().toString();
               String subtitle = binding.txtUpsubtitle.getText().toString();
               String notes = binding.txtUpnotes.getText().toString();

                Updatenotes(title, subtitle, notes);
            }
        });
    }

    private void Updatenotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d, yyyy", date.getTime());

        Notes upnotes = new Notes();
        upnotes.id=iid;
        upnotes.notesTitle=title;
        upnotes.notesSubtitle=subtitle;
        upnotes.notes=notes;
        upnotes.notesDate = sequence.toString();
        upnotes.notesPriority = priority;

        notesViewModel.updateNote(upnotes);

        Toast.makeText(UpdateNotesActivity.this, "Notes has been updated successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.updatemenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.deletenote){
            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdateNotesActivity.this);
            View view = LayoutInflater.from(UpdateNotesActivity.this).
                    inflate(R.layout.custon_delete_sheet,(ConstraintLayout) findViewById(R.id.bottomSheet));

            sheetDialog.setContentView(view);
            sheetDialog.show();
            TextView yes,no;

            yes = (TextView) view.findViewById(R.id.btn_deleteyes);
            no = (TextView) view.findViewById(R.id.btn_deleteno);

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notesViewModel.deleteNote(iid);
                    finish();
                    sheetDialog.dismiss();
                }
            });

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sheetDialog.dismiss();
                }
            });

        }
        return true;
    }
}
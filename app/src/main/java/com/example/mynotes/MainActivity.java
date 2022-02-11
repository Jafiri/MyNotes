package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.mynotes.Activity.InsertNotesActivity;
import com.example.mynotes.Adapter.NotesAdapter;
import com.example.mynotes.Models.Notes;
import com.example.mynotes.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotes;
    RecyclerView notesRecycleView;
    TextView noFilter, LowtoHigh, HightoLow;


    NotesViewModel notesViewModel;
    NotesAdapter adapter;
    List<Notes> filterNotesAllList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        noFilter = (TextView) findViewById(R.id.txt_nofilter);
        LowtoHigh = (TextView) findViewById(R.id.txt_lowtohigh);
        HightoLow = (TextView) findViewById(R.id.txt_hightolow);
        newNotes = (FloatingActionButton) findViewById(R.id.btn_newNotes);
        notesRecycleView = (RecyclerView) findViewById(R.id.notesRV);

        noFilter.setBackgroundResource(R.drawable.sortbackground_selected);

        noFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(0);
                noFilter.setBackgroundResource(R.drawable.sortbackground_selected);
                LowtoHigh.setBackgroundResource(R.drawable.sortbackground);
                HightoLow.setBackgroundResource(R.drawable.sortbackground);

            }
        });

        LowtoHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(1);
                noFilter.setBackgroundResource(R.drawable.sortbackground);
                LowtoHigh.setBackgroundResource(R.drawable.sortbackground_selected);
                HightoLow.setBackgroundResource(R.drawable.sortbackground);
            }
        });

        HightoLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(2);
                noFilter.setBackgroundResource(R.drawable.sortbackground);
                LowtoHigh.setBackgroundResource(R.drawable.sortbackground);
                HightoLow.setBackgroundResource(R.drawable.sortbackground_selected);
            }
        });


        notesViewModel = ViewModelProviders.of(MainActivity.this).get(NotesViewModel.class);

        newNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));

            }
        });

        notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                setAdapter(notes);
                filterNotesAllList = notes;
            }
        });


    }

    private void loadData(int i) {
        if (i == 0) {
            notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filterNotesAllList = notes;
                }
            });
        }

        if (i == 1) {
            notesViewModel.lowTohigh.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filterNotesAllList = notes;
                }
            });
        }

        if (i == 2) {
            notesViewModel.highTolow.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filterNotesAllList = notes;
                }
            });
        }
    }


    private void setAdapter(List<Notes> notes) {
        notesRecycleView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new NotesAdapter(this, notes);
        notesRecycleView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.search_bar,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView =(SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Notes here.....");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                notesFilter(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void notesFilter(String newText) {

        ArrayList<Notes> filternames = new ArrayList<>();

        for(Notes notes : this.filterNotesAllList){

            if(notes.notesTitle.contains(newText)||notes.notesSubtitle.contains(newText)){

                filternames.add(notes);

            }
        }
        this.adapter.searchNotes(filternames);
    }
}

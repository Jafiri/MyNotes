package com.example.mynotes.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.Activity.UpdateNotesActivity;
import com.example.mynotes.Models.Notes;
import com.example.mynotes.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    Context context;
    List<Notes> noteslist;
    List<Notes> allNotesItem;


    public NotesAdapter(Context context, List<Notes> noteslist) {
        this.context = context;
        this.noteslist = noteslist;
        allNotesItem = new ArrayList<>(noteslist);
    }

    public  void searchNotes(List<Notes> filterdNotes){

        this.noteslist=filterdNotes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_notescard, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Notes note = noteslist.get(position);

        if(note.notesPriority != null && note.notesPriority.equals("1")){
            holder.notesPriority.setBackgroundResource(R.drawable.greencircle);
        }else if(note.notesPriority != null && note.notesPriority.equals("2")){
            holder.notesPriority.setBackgroundResource(R.drawable.yellowcircle);
        }else if(note.notesPriority != null && note.notesPriority.equals("3")){
            holder.notesPriority.setBackgroundResource(R.drawable.bluecircle);
        }

        holder.notesTitle.setText(note.notesTitle);
        holder.notesSubtitle.setText(note.notes);
        holder.NotesDate.setText(note.notesDate);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, UpdateNotesActivity.class);
                intent.putExtra("id",note.id);
                intent.putExtra("title",note.notesTitle);
                intent.putExtra("subtitle",note.notesSubtitle);
                intent.putExtra("notes",note.notes);
                intent.putExtra("priority",note.notesPriority);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return noteslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View notesPriority;
        TextView notesTitle, notesSubtitle, NotesDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notesPriority = (View) itemView.findViewById(R.id.notes_priority);
            notesTitle = (TextView) itemView.findViewById(R.id.notes_title);
            notesSubtitle = (TextView) itemView.findViewById(R.id.notes_notes);
            NotesDate = (TextView) itemView.findViewById(R.id.notes_date);

        }
    }
}

package com.example.mvvmnotes.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmnotes.Model.Notes;
import com.example.mvvmnotes.R;
import com.example.mvvmnotes.databinding.NotesitemBinding;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {

    private FragmentActivity activity;
    private List<Notes> notes;
    private List<Notes> searchedNotes;
    private onNoteListener mOnNoteListener;

    public NotesAdapter(FragmentActivity activity, List<Notes> notes, onNoteListener OnNoteListener) {
        this.activity=activity;
        this.notes=notes;
        this.searchedNotes = new ArrayList<>(notes);
        this.mOnNoteListener=OnNoteListener;

    }

    public void searchedNotes(List<Notes> searchedNotes){
        this.notes=searchedNotes;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public notesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new notesViewHolder(LayoutInflater.from(activity).inflate(R.layout.notesitem,parent,false),mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull notesViewHolder holder, int position) {
        Notes note = notes.get(position);
        holder.notesitemBinding.notesTitle.setText(note.notesTitle);
        holder.notesitemBinding.notesSubtitle.setText(note.notesSubtitle);
        holder.notesitemBinding.notesDate.setText(note.notesDate);

        if(note.notesPriority==null || note.notesPriority.equals("1")){
            holder.notesitemBinding.notesPriority.setBackgroundResource(R.drawable.green_circle);
        }
        else if(note.notesPriority.equals("2")){
            holder.notesitemBinding.notesPriority.setBackgroundResource(R.drawable.yellow_circle);
        }
        else if(note.notesPriority.equals("3")){
            holder.notesitemBinding.notesPriority.setBackgroundResource(R.drawable.red_circle);
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class notesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public NotesitemBinding notesitemBinding;
        onNoteListener mOnNoteListener;

        public notesViewHolder(@NonNull View itemView, onNoteListener OnNoteListener) {
            super(itemView);
            notesitemBinding=NotesitemBinding.bind(itemView);
            mOnNoteListener=OnNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface onNoteListener{
        void onNoteClick(int position);
    }
}

package com.example.mvvmnotes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mvvmnotes.Model.Notes;
import com.example.mvvmnotes.ViewModel.NotesViewModel;
import com.example.mvvmnotes.databinding.FragmentInsertNotesBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class InsertNotes extends Fragment {

    private FragmentInsertNotesBinding binding;
    private String notesTitle, notesSubTitle, notesBody;

    private NotesViewModel notesViewModel;

    private String priority = "1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insert_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding=FragmentInsertNotesBinding.bind(view);

        notesViewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);

        binding.insertNotesBtn.setOnClickListener(view1 -> {
            notesTitle=binding.notesTitle.getText().toString();
            notesSubTitle=binding.notesSubTitle.getText().toString();
            notesBody=binding.notesBody.getText().toString();

            createNotes(notesTitle,notesSubTitle,notesBody,view);
        });

        binding.greenPriority.setOnClickListener(view1 -> {
            priority="1";
            binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
        });

        binding.yellowPriority.setOnClickListener(view1 -> {
            priority="2";
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.redPriority.setImageResource(0);
        });

        binding.redPriority.setOnClickListener(view1 -> {
            priority="3";
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
        });
    }

    private void createNotes(String notesTitle, String notesSubTitle, String notesBody,@NonNull View view) {
        Notes note = new Notes();
        note.notesTitle=notesTitle;
        note.notesSubtitle=notesSubTitle;
        note.notesData=notesBody;
        note.notesPriority=priority;

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");

        note.notesDate = format.format(date);

        notesViewModel.insertNotes(note);

        Toast.makeText(getContext(), "Note Created Sucessfully", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).popBackStack();
    }
}
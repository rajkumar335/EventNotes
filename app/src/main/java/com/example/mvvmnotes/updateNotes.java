package com.example.mvvmnotes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mvvmnotes.Model.Notes;
import com.example.mvvmnotes.ViewModel.NotesViewModel;
import com.example.mvvmnotes.databinding.DeleteBottomSheetBinding;
import com.example.mvvmnotes.databinding.FragmentUpdateNotesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

public class updateNotes extends Fragment {

    FragmentUpdateNotesBinding binding;
    private String notesTitle, notesSubTitle, notesBody, notesDate;
    private int noteID;

    private NotesViewModel notesViewModel;

    private String priority = "1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding=FragmentUpdateNotesBinding.bind(view);

        notesViewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);

        updateNotesArgs args = updateNotesArgs.fromBundle(getArguments());
        notesTitle = args.getNotesTitle();
        notesSubTitle = args.getNotesSubTitle();
        notesBody = args.getNoteBody();
        notesDate= args.getNoteDate();
        priority = args.getNotesPriority();
        noteID = args.getNoteID();

        binding.updatedTitle.setText(notesTitle);
        binding.updatedSubTitle.setText(notesSubTitle);
        binding.updatedNote.setText(notesBody);

        if(priority.equals("1")){
            binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
        }
        else if(priority.equals("2")){
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.redPriority.setImageResource(0);
        }
        else if(priority.equals("3")){
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
        }

        binding.updateNotesBtn.setOnClickListener(view1 -> {
            String title = binding.updatedTitle.getText().toString();
            String subTitle = binding.updatedSubTitle.getText().toString();
            String noteBody = binding.updatedNote.getText().toString();

            UpdateNotes(title,subTitle,noteBody);
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

    private void UpdateNotes(String notesTitle, String notesSubTitle, String notesBody) {
        Notes updatedNote = new Notes();
        updatedNote.notesTitle=notesTitle;
        updatedNote.notesSubtitle=notesSubTitle;
        updatedNote.notesData=notesBody;
        updatedNote.notesPriority=priority;
        updatedNote.notesID=noteID;

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");

        updatedNote.notesDate = format.format(date);

        notesViewModel.updateNotes(updatedNote);
        Toast.makeText(getActivity(), "Note Updated Successfully!", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(getView()).popBackStack();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.delete_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_note){
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(),R.style.BottomSheetStyle);
            View v =LayoutInflater.from(getActivity()).inflate(R.layout.delete_bottom_sheet,(LinearLayout) getView().findViewById(R.id.design_bottom_sheet));
            bottomSheetDialog.setContentView(v);

            DeleteBottomSheetBinding bottomSheetBinding = DeleteBottomSheetBinding.bind(v);

            bottomSheetBinding.noButton.setOnClickListener(view -> {
                bottomSheetDialog.dismiss();
            });

            bottomSheetBinding.yesButton.setOnClickListener(view -> {
                notesViewModel.deleteNodes(noteID);
                bottomSheetDialog.dismiss();
                Navigation.findNavController(getView()).popBackStack();
            });


            bottomSheetDialog.show();

        }
        return true;
    }
}

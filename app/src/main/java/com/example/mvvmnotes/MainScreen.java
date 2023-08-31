package com.example.mvvmnotes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.mvvmnotes.Adapter.NotesAdapter;
import com.example.mvvmnotes.Model.Notes;
import com.example.mvvmnotes.ViewModel.NotesViewModel;
import com.example.mvvmnotes.databinding.FragmentMainScreenBinding;

import java.util.ArrayList;
import java.util.List;

public class MainScreen extends Fragment implements NotesAdapter.onNoteListener {

    private FragmentMainScreenBinding binding;
    private NotesViewModel notesViewModel;
    private RecyclerView notesRecyclerView;
    private NotesAdapter notesAdapter;
    private List<Notes> mnotes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_main_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMainScreenBinding.bind(view);

        notesViewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);
        notesRecyclerView = binding.notesView;

        binding.filterNo.setBackgroundResource(R.drawable.filter_selected_shape);
//        notesViewModel.allNotes.observe(getViewLifecycleOwner(),notes -> {
//            notesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
//            notesAdapter = new NotesAdapter(getActivity(),notes);
//            notesRecyclerView.setAdapter(notesAdapter);
//        });

        notesViewModel.allNotes.observe(getViewLifecycleOwner(), notes -> {
            mnotes = notes;
            setNotesAdapter(notes);
        });


        binding.addNotes.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_mainScreen_to_insertNotes);
        });

        binding.filterNo.setOnClickListener(view1 -> {
            loadData(0);
            binding.filterHighToLow.setBackgroundResource(R.drawable.filter_shape);
            binding.filterLowToHigh.setBackgroundResource(R.drawable.filter_shape);
            binding.filterNo.setBackgroundResource(R.drawable.filter_selected_shape);
        });

        binding.filterHighToLow.setOnClickListener(view1 -> {
            loadData(1);
            binding.filterHighToLow.setBackgroundResource(R.drawable.filter_selected_shape);
            binding.filterLowToHigh.setBackgroundResource(R.drawable.filter_shape);
            binding.filterNo.setBackgroundResource(R.drawable.filter_shape);
        });

        binding.filterLowToHigh.setOnClickListener(view1 -> {
            loadData(2);
            binding.filterHighToLow.setBackgroundResource(R.drawable.filter_shape);
            binding.filterLowToHigh.setBackgroundResource(R.drawable.filter_selected_shape);
            binding.filterNo.setBackgroundResource(R.drawable.filter_shape);
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.search_note);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Search your notes here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                SearchNotes(s);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.calendar){
            Navigation.findNavController(getView()).navigate(R.id.action_mainScreen_to_signInFragment);
        }
        return true;
    }

    private void loadData(int i) {
        if(i==0){
            notesViewModel.allNotes.observe(getViewLifecycleOwner(), notes -> {
                mnotes = notes;
                setNotesAdapter(notes);
            });
        }
        else if(i==1){
            notesViewModel.allNotesHighToLow.observe(getViewLifecycleOwner(), notes -> {
                mnotes = notes;
                setNotesAdapter(notes);
            });
        }
        else if(i==2){
            notesViewModel.allNotesLowToHigh.observe(getViewLifecycleOwner(), notes -> {
                mnotes = notes;
                setNotesAdapter(notes);
            });
        }
    }

    public void setNotesAdapter(List<Notes> notes){
        notesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        notesAdapter = new NotesAdapter(getActivity(), notes, this);
        notesRecyclerView.setAdapter(notesAdapter);
    }
    @Override
    public void onNoteClick(int position) {
        Notes notes = mnotes.get(position);
        MainScreenDirections.ActionMainScreenToUpdateNotes action = MainScreenDirections.actionMainScreenToUpdateNotes(notes.notesID);

        action.setNotesTitle(notes.notesTitle);
        action.setNotesSubTitle(notes.notesSubtitle);
        action.setNotesPriority(notes.notesPriority);
        action.setNoteBody(notes.notesData);
        action.setNoteDate(notes.notesDate);
        action.setNoteID(notes.notesID);

        Navigation.findNavController(getView()).navigate(action);
    }



    private void SearchNotes(String s) {
        ArrayList<Notes> searchedNotes = new ArrayList<>();
        for(Notes notes:mnotes){
            if(notes.notesTitle.contains(s) || notes.notesSubtitle.contains(s)){
                searchedNotes.add(notes);
            }
        }
        this.notesAdapter.searchedNotes(searchedNotes);
    }
}
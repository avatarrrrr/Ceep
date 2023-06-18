package br.com.alura.ceep.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.dao.NoteDAO;
import br.com.alura.ceep.model.Note;
import br.com.alura.ceep.recyclerview.adapter.NotesListAdapter;

public class NotesListActivity extends AppCompatActivity {
    final NoteDAO notesDAO = new NoteDAO();
    NotesListAdapter adapter;
    RecyclerView notesListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        bindViews();
        makeDataTest();
        setListArtifacts();
        configListView();
    }

    private void makeDataTest() {
        final List<Note> notesTest = Collections.nCopies(1000, new Note("Hello World!", "Make the world a place better! ✨"));
        notesDAO.insertList(notesTest);
    }

    private void bindViews() {
        notesListView = findViewById(R.id.notes_list);
    }

    private void setListArtifacts() {
        adapter = new NotesListAdapter(notesDAO.all(), this);
    }

    private void configListView() {
        notesListView.setAdapter(adapter);
    }
}
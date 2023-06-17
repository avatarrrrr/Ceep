package br.com.alura.ceep.ui.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.dao.NoteDAO;
import br.com.alura.ceep.model.Note;
import br.com.alura.ceep.ui.adapter.NotesListAdapter;

public class NotesListActivity extends AppCompatActivity {
    final NoteDAO notesDAO = new NoteDAO();
    NotesListAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        bindViews();
        configListView();
    }

    private void bindViews() {
        listView = findViewById(R.id.notes_list);
    }

    private void configListView() {
        final List<Note> notesTest = Collections.nCopies(1000, new Note("Hello World!", "Make the world a place better! ✨"));
        notesDAO.insertList(notesTest);
        adapter = new NotesListAdapter(this, notesDAO.all());
        listView.setAdapter(adapter);
    }
}
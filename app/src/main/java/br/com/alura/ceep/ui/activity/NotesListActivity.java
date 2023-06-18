package br.com.alura.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import br.com.alura.ceep.R;
import br.com.alura.ceep.dao.NoteDAO;
import br.com.alura.ceep.recyclerview.adapter.NotesListAdapter;

public class NotesListActivity extends AppCompatActivity {
    final NoteDAO notesDAO = new NoteDAO();
    NotesListAdapter adapter;
    RecyclerView notesListView;
    TextView insertView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        bindViews();
        setListArtifacts();
        configListView();
        configInsertNoteOnClickBehavior();
    }

    @Override
    protected void onResume() {
        setListArtifacts();
        configListView();
        super.onResume();
    }

    private void bindViews() {
        notesListView = findViewById(R.id.notes_list);
        insertView = findViewById(R.id.notes_list_insert_note);
    }

    private void setListArtifacts() {
        adapter = new NotesListAdapter(notesDAO.all(), this);
    }

    private void configListView() {
        notesListView.setAdapter(adapter);
    }

    private void configInsertNoteOnClickBehavior() {
        insertView.setOnClickListener(v -> startActivity(
                new Intent(
                        NotesListActivity.this,
                        NewNoteActivity.class
                )
        ));
    }
}
package br.com.alura.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import br.com.alura.ceep.R;
import br.com.alura.ceep.dao.NoteDAO;
import br.com.alura.ceep.model.Note;
import br.com.alura.ceep.recyclerview.adapter.NotesListAdapter;

public class NotesListActivity extends AppCompatActivity {
    final NoteDAO notesDAO = new NoteDAO();
    final ActivityResultLauncher<Intent> activityResultLauncher = registerActivityResult();
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

    private ActivityResultLauncher<Intent> registerActivityResult() {
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this::onActivityResult);
    }

    private void onActivityResult(ActivityResult result) {
        final Intent intent = result.getData();
        final boolean resultCodeIsValid = result.getResultCode() == ActivityConstants.RESULT_OK;

        if (resultCodeIsValid && intent != null && intent.hasExtra(ActivityConstants.NOTE_TRANSFER_KEY)) {
            final Note note = intent.getParcelableExtra(ActivityConstants.NOTE_TRANSFER_KEY);
            insertNote(note);
        }
    }

    private void insertNote(Note note) {
        notesDAO.insert(note);
        adapter.insert(note);
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
        insertView.setOnClickListener(
                v -> activityResultLauncher.launch(
                        new Intent(
                                NotesListActivity.this,
                                NewNoteActivity.class
                        )
                )
        );
    }
}
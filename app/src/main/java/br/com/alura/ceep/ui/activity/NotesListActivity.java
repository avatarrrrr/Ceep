package br.com.alura.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import br.com.alura.ceep.R;
import br.com.alura.ceep.dao.NoteDAO;
import br.com.alura.ceep.model.Note;
import br.com.alura.ceep.recyclerview.adapter.NotesListAdapter;
import br.com.alura.ceep.ui.activity.controllers.NotesListActivityController;
import br.com.alura.ceep.ui.adapter.listener.OnItemClickListener;

public class NotesListActivity extends AppCompatActivity {
    final NotesListActivityController controller = new NotesListActivityController(this::insertNoteOnAdapter, this::updateNoteOnAdapter);
    final ActivityResultLauncher<Intent> activityResultLauncher = registerActivityResult();
    NotesListAdapter adapter;
    RecyclerView notesListView;
    TextView insertView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        controller.makeDataTest();
        bindViews();
        setListArtifacts();
        configListView();
        configInsertNoteOnClickBehavior();
    }


    private ActivityResultLauncher<Intent> registerActivityResult() {
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), controller::onActivityResult);
    }


    private void bindViews() {
        notesListView = findViewById(R.id.notes_list);
        insertView = findViewById(R.id.notes_list_insert_note);
    }

    private void setListArtifacts() {
        adapter = new NotesListAdapter(new NoteDAO().all(), this, onItemClickBehavior());
    }

    @NonNull
    private OnItemClickListener onItemClickBehavior() {
        return (note, position) -> {
            final Intent intent = generateIntentToNewNoteForm();
            intent.putExtra(ActivityConstants.NOTE_TRANSFER_KEY, note);
            intent.putExtra(ActivityConstants.NOTE_POSITION_TRANSFER_KEY, position);
            activityResultLauncher.launch(intent);
        };
    }

    private void configListView() {
        notesListView.setAdapter(adapter);
    }

    private void configInsertNoteOnClickBehavior() {
        insertView.setOnClickListener(
                v -> activityResultLauncher.launch(
                        generateIntentToNewNoteForm()
                )
        );
    }

    @NonNull
    private Intent generateIntentToNewNoteForm() {
        return new Intent(
                NotesListActivity.this,
                NewNoteActivity.class
        );
    }

    private void insertNoteOnAdapter(Note note) {
        adapter.insert(note);
    }

    private void updateNoteOnAdapter(Note note, int notePosition) {
        adapter.replaceNoteInPosition(note, notePosition);
    }
}
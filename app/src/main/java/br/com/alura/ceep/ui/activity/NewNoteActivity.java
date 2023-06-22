package br.com.alura.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.ceep.R;
import br.com.alura.ceep.model.Note;

public class NewNoteActivity extends AppCompatActivity {
    EditText titleEditText;
    EditText descriptionEditText;

    Boolean isEdit = false;
    int noteEditPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        bindViews();
        verifyIsEditMode();
    }

    private void bindViews() {
        titleEditText = findViewById(R.id.new_note_form_title);
        descriptionEditText = findViewById(R.id.new_note_form_description);
    }

    private void verifyIsEditMode() {
        final Intent intent = getIntent();
        if (intent.hasExtra(ActivityConstants.NOTE_TRANSFER_KEY) && intent.hasExtra(ActivityConstants.NOTE_POSITION_TRANSFER_KEY)) {
            isEdit = true;
            noteEditPosition = intent.getIntExtra(ActivityConstants.NOTE_POSITION_TRANSFER_KEY, -1);
            bindIntentOnViews(intent);
        }
    }

    private void bindIntentOnViews(Intent intent) {
        final Note note = intent.getParcelableExtra(ActivityConstants.NOTE_TRANSFER_KEY);
        titleEditText.setText(note.getTitle());
        descriptionEditText.setText(note.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form_save_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        saveNote(item);
        return super.onOptionsItemSelected(item);
    }

    private void saveNote(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_form_save_note_button) {
            final Note newNote = new Note(titleEditText.getText().toString(), descriptionEditText.getText().toString());
            final Intent intent = new Intent().putExtra(ActivityConstants.NOTE_TRANSFER_KEY, newNote);
            intent.putExtra(ActivityConstants.NOTE_POSITION_TRANSFER_KEY, noteEditPosition);
            setResult(isEdit ? ActivityConstants.RESULT_EDIT_NOTE : ActivityConstants.RESULT_CREATE_NOTE, intent);
            finish();
        }
    }
}
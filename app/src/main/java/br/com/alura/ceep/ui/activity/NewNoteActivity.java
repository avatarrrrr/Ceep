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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        bindViews();
    }

    private void bindViews() {
        titleEditText = findViewById(R.id.new_note_form_title);
        descriptionEditText = findViewById(R.id.new_note_form_description);
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
            setResult(ActivityConstants.RESULT_OK , intent);
            finish();
        }
    }
}
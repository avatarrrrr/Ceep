package br.com.alura.ceep.ui.activity.controllers;

import android.content.Intent;

import androidx.activity.result.ActivityResult;

import br.com.alura.ceep.dao.NoteDAO;
import br.com.alura.ceep.model.Note;
import br.com.alura.ceep.ui.activity.ActivityConstants;

public class NotesListActivityController {
    private final NoteDAO notesDAO = new NoteDAO();
    private final InsertNote insertNote;
    private final UpdateNote updateNote;

    public NotesListActivityController(InsertNote insertNote, UpdateNote updateNote) {
        this.insertNote = insertNote;
        this.updateNote = updateNote;
    }

    public void makeDataTest() {
        for (int i = 0; i < 10; i++) {
            notesDAO.insert(new Note("Hello World " + i + "!", "Make the world a place better! ✨"));
        }
    }

    public void onActivityResult(ActivityResult result) {
        final Intent intent = result.getData();
        final boolean resultCodeIsCreateNote = result.getResultCode() == ActivityConstants.RESULT_CREATE_NOTE;
        final boolean resultCodeIsEditNote = result.getResultCode() == ActivityConstants.RESULT_EDIT_NOTE;

        createNote(intent, resultCodeIsCreateNote);
        replaceNoteOnPosition(intent, resultCodeIsEditNote);
    }

    private void createNote(Intent intent, boolean resultCodeIsCreateNote) {
        if (resultCodeIsCreateNote && intent != null && intent.hasExtra(ActivityConstants.NOTE_TRANSFER_KEY)) {
            final Note note = intent.getParcelableExtra(ActivityConstants.NOTE_TRANSFER_KEY);
            insertNote(note);
        }
    }

    private void replaceNoteOnPosition(Intent intent, boolean resultCodeIsEditNote) {
        if (resultCodeIsEditNote &&
                intent != null &&
                intent.hasExtra(ActivityConstants.NOTE_TRANSFER_KEY) &&
                intent.hasExtra(ActivityConstants.NOTE_POSITION_TRANSFER_KEY)
        ) {
            final Note note = intent.getParcelableExtra(ActivityConstants.NOTE_TRANSFER_KEY);
            final int notePosition = intent.getIntExtra(ActivityConstants.NOTE_POSITION_TRANSFER_KEY, -1);
            setNote(note, notePosition);
        }
    }

    private void insertNote(Note note) {
        notesDAO.insert(note);
        insertNote.insertNoteOnAdapter(note);
    }

    private void setNote(Note note, int notePosition) {
        notesDAO.update(notePosition, note);
        updateNote.updateNoteOnAdapter(note, notePosition);
    }

    public interface InsertNote {
        void insertNoteOnAdapter(Note note);
    }

    public interface UpdateNote {
        void updateNoteOnAdapter(Note note, int notePosition);
    }

}


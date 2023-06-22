package br.com.alura.ceep.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.alura.ceep.model.Note;

public class NoteDAO {

    private final static ArrayList<Note> NOTES = new ArrayList<>();

    public List<Note> all() {
        return new ArrayList<>(NOTES);
    }

    public void insert(Note... notes) {
        NoteDAO.NOTES.addAll(Arrays.asList(notes));
    }

    public void update(int position, Note note) {
        NOTES.set(position, note);
    }

    public void delete(int position) {
        NOTES.remove(position);
    }

    public void swap(int startPosition, int endPosition) {
        Collections.swap(NOTES, startPosition, endPosition);
    }

    public void deleteAll() {
        NOTES.clear();
    }
}

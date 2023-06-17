package br.com.alura.ceep.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.model.Note;

public class NotesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final List<Note> notes;
    final Context context;

    public NotesListAdapter(List<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View newView = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new NotesListAdapterHolder(newView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Note note = notes.get(position);
        ((NotesListAdapterHolder) holder).titleView.setText(note.getTitle());
        ((NotesListAdapterHolder) holder).descriptionView.setText(note.getDescription());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    private static class NotesListAdapterHolder extends RecyclerView.ViewHolder {
        public final TextView titleView;
        public final TextView descriptionView;

        public NotesListAdapterHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.item_note_title);
            descriptionView = itemView.findViewById(R.id.item_note_description);
        }
    }
}

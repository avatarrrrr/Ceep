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

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NotesListAdapterHolder> {
    final List<Note> notes;
    final Context context;

    public NotesListAdapter(List<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public NotesListAdapter.NotesListAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View newView = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new NotesListAdapterHolder(newView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesListAdapter.NotesListAdapterHolder holder, int position) {
        final Note note = notes.get(position);
        holder.bindNote(note);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void insert(Note note){
        notes.add(note);
        notifyItemInserted(notes.indexOf(note));
    }

    public static class NotesListAdapterHolder extends RecyclerView.ViewHolder {
        private final TextView titleView;
        private final TextView descriptionView;

        public NotesListAdapterHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.item_note_title);
            descriptionView = itemView.findViewById(R.id.item_note_description);
        }

        void bindNote(Note note) {
            titleView.setText(note.getTitle());
            descriptionView.setText(note.getDescription());
        }
    }
}

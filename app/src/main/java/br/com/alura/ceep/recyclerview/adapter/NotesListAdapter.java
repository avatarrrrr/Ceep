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
import br.com.alura.ceep.ui.adapter.listener.OnItemClickListener;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NotesListAdapterHolder> {
    final List<Note> notes;
    final Context context;
    final OnItemClickListener onClickBehavior;

    public NotesListAdapter(List<Note> notes, Context context, OnItemClickListener onClickBehavior) {
        this.notes = notes;
        this.context = context;
        this.onClickBehavior = onClickBehavior;
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
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void insert(Note note) {
        notes.add(note);
        notifyItemInserted(notes.indexOf(note));
    }

    public void replaceNoteInPosition(Note note, int notePosition) {
        notes.set(notePosition, note);
        notifyItemChanged(notePosition);
    }

    public class NotesListAdapterHolder extends RecyclerView.ViewHolder {
        private final TextView titleView;
        private final TextView descriptionView;

        public NotesListAdapterHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.item_note_title);
            descriptionView = itemView.findViewById(R.id.item_note_description);
        }

        void bind(Note note) {
            bindNote(note);
            setClickBehavior(note);
        }

        private void setClickBehavior(Note note) {
            this.itemView.setOnClickListener(v -> onClickBehavior.onItemClick(note, getAdapterPosition()));
        }

        private void bindNote(@NonNull Note note) {
            titleView.setText(note.getTitle());
            descriptionView.setText(note.getDescription());
        }
    }
}

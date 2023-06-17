package br.com.alura.ceep.ui.view_holders;

import android.view.View;
import android.widget.TextView;

import br.com.alura.ceep.R;

public class NotesListAdapterViewHolder {
    final public TextView title;
    final public TextView description;

    public NotesListAdapterViewHolder(View view) {
        this.title = view.findViewById(R.id.item_note_title);
        this.description = view.findViewById(R.id.item_note_description);
    }
}

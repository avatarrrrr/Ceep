package br.com.alura.ceep.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.model.Note;
import br.com.alura.ceep.ui.view_holders.NotesListAdapterViewHolder;

public class NotesListAdapter extends BaseAdapter {

    private final Context context;
    private final List<Note> notes;

    public NotesListAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Note getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = createViewOrReuseConvertView(convertView, viewGroup);
        bindNoteToViews((NotesListAdapterViewHolder) view.getTag(), position);

        return view;
    }


    private View createViewOrReuseConvertView(View convertView, ViewGroup viewGroup) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_note, viewGroup, false);
            view.setTag(new NotesListAdapterViewHolder(view));
        } else {
            view = convertView;
        }
        return view;
    }

    private void bindNoteToViews(NotesListAdapterViewHolder viewHolder, int position) {
        Note note = notes.get(position);
        viewHolder.title.setText(note.getTitle());
        viewHolder.description.setText(note.getDescription());
    }
}

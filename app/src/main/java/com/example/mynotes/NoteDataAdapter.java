package com.example.mynotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteDataAdapter extends BaseAdapter {
    private List<NoteData> noteDataList;
    private LayoutInflater inflater;

    NoteDataAdapter(Context context, List<NoteData> noteDataList) {
        if (noteDataList == null) {
            this.noteDataList = new ArrayList<>();
        } else {
            this.noteDataList = noteDataList;
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    void addNote(NoteData note) {
        this.noteDataList.add(note);
        notifyDataSetChanged();
    }

    void deleteNote(int position) {
        noteDataList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return noteDataList.size();
    }

    @Override
    public NoteData getItem(int position) {
        if (position < noteDataList.size()) {
            return noteDataList.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_list_view, parent, false);
        }

        NoteData noteData = noteDataList.get(position);

        TextView title = view.findViewById(R.id.title);
        TextView subtitle = view.findViewById(R.id.subtitle);
        TextView deadline = view.findViewById(R.id.deadline);

        title.setText(noteData.getTitle());
        subtitle.setText(noteData.getSubtitle());
        deadline.setText(noteData.getDeadline());

        return view;
    }
}
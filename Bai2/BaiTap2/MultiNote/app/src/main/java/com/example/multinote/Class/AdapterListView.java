package com.example.multinote.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.multinote.Class.Note;
import com.example.multinote.R;

import java.util.List;

public class AdapterListView extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Note> noteList;

    public AdapterListView(Context context, int layout, List<Note> noteList) {
        this.context = context;
        this.layout = layout;
        this.noteList = noteList;
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);
        Note note = noteList.get(position);

        TextView title = convertView.findViewById(R.id.title);
        TextView date = convertView.findViewById(R.id.date);
        TextView content = convertView.findViewById(R.id.content);
        TextView number = convertView.findViewById(R.id.number);
        title.setText(note.getTitle());
        date.setText(note.getDate());
        content.setText(note.getContent());
        int pstemp = position+1;
        number.setText("Note - "+pstemp+"");
     return convertView;
    }
}







package com.example.wolverine.demonoteapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.example.wolverine.demonoteapp.data.NoteContract.NoteEntry;

import java.text.SimpleDateFormat;

/**
 * Created by wolverine on 20/09/18.
 */

public class NotesCursorloader extends CursorAdapter {
    private Context mContext;

    public NotesCursorloader(Context context, Cursor c) {
        super(context, c, 0);
        mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, final Cursor cursor) {

        TextView NoteTexttxt = (TextView)view.findViewById(R.id.Note_Text);
        TextView NoteDatetxt = (TextView)view.findViewById(R.id.Note_Date);

        int NoteTextColumnIndex = cursor.getColumnIndex(NoteEntry.NOTE_TEXT);
        int NoteDateColumnIndex = cursor.getColumnIndex(NoteEntry.NOTE_DATE);

        String NoteText = cursor.getString(NoteTextColumnIndex);
        String NoteDate = cursor.getString(NoteDateColumnIndex);

        String date_time = dateFormater(NoteDate);

        NoteTexttxt.setText(NoteText);
        NoteDatetxt.setText(date_time);
    }

    private String dateFormater(String noteDate) {
        Long date = Long.parseLong(noteDate);

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss aaa");

        return sdf.format(date);
    }
}

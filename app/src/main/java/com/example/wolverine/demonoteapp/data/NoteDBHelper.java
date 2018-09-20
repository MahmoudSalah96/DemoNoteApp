package com.example.wolverine.demonoteapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.wolverine.demonoteapp.data.NoteContract.NoteEntry;
import static android.R.attr.version;

/**
 * Created by wolverine on 19/09/18.
 */

public class NoteDBHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = NoteDBHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "Note.db";

    private static final int DATABASE_VERSION = 1;

    public NoteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_NOTE_TABLE = "CREATE TABLE " + NoteEntry.TABLE_NAME + " ("
                + NoteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NoteEntry.NOTE_TEXT + " TEXT NOT NULL, "
                + NoteEntry.NOTE_DATE + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_NOTE_TABLE);
        Log.d(LOG_TAG, SQL_CREATE_NOTE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_NOTE_ITEM_ENTRY =
                "DELETE FROM " + NoteEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_NOTE_ITEM_ENTRY);
    }
}

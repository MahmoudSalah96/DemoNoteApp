package com.example.wolverine.demonoteapp;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wolverine.demonoteapp.data.NoteContract.NoteEntry;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int NOTE_LOADER = 0;

    NotesCursorloader mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fabutton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Editor_Activity.class);
                startActivity(intent);
            }
        });

        ListView noteListView = (ListView)findViewById(R.id.list);

        View emptyView = findViewById(R.id.empty_view);
        noteListView.setEmptyView(emptyView);

        mAdapter = new NotesCursorloader(this,null);
        noteListView.setAdapter(mAdapter);

        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Editor_Activity.class);

                Uri currentNoteUri = ContentUris.withAppendedId(NoteEntry.CONTENT_URI,id);

                intent.setData(currentNoteUri);
                startActivity(intent);
            }
        });

        getLoaderManager().initLoader(NOTE_LOADER,null,this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete_all_notes :
                deleteAllNotesItem();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteAllNotesItem() {
        if(mAdapter.isEmpty())
            Toast.makeText(this, R.string.empty_list,Toast.LENGTH_SHORT).show();
        else {
            getContentResolver().delete(NoteEntry.CONTENT_URI, null, null);
            Toast.makeText(this, R.string.deleted,Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String [] projection = {
                NoteEntry._ID,
                NoteEntry.NOTE_TEXT,
                NoteEntry.NOTE_DATE};
        return new CursorLoader(this,
                NoteEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}

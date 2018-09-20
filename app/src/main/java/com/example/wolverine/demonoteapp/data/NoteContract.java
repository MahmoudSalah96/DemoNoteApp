package com.example.wolverine.demonoteapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by wolverine on 19/09/18.
 */

public class NoteContract {

    public static final String CONTENT_AUTHORITY = "com.example.wolverine.demonoteapp";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_NOTE = "NOTES";

    public static final class NoteEntry implements BaseColumns {

        public final static String TABLE_NAME = "note";

        public final static String _ID = BaseColumns._ID;

        public final static String NOTE_TEXT = "noteText";

        public final static String NOTE_DATE = "Date";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NOTE);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NOTE;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NOTE;
    }
}

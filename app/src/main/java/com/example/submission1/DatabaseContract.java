package com.example.submission1;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_FAV = "fav";

    static final class NoteColumns implements BaseColumns {
        static String TITLE = "title";
        static String DATE = "date";
        static String IMAGE = "image";
        static String OVERVIEW = "overview";
    }
}

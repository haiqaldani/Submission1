package com.example.submission1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.submission1.DatabaseContract.NoteColumns.DATE;
import static com.example.submission1.DatabaseContract.NoteColumns.IMAGE;
import static com.example.submission1.DatabaseContract.NoteColumns.OVERVIEW;
import static com.example.submission1.DatabaseContract.NoteColumns.TITLE;
import static com.example.submission1.DatabaseContract.TABLE_FAV;

public class FavoriteHelper {
    private static final String DATABASE_TABLE = TABLE_FAV;
    private static DatabaseHelper dataBaseHelper;
    private static FavoriteHelper INSTANCE;
    private static SQLiteDatabase database;

    public FavoriteHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static FavoriteHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {

        database = dataBaseHelper.getWritableDatabase();
    }

    public void close() {
        dataBaseHelper.close();
        if (database.isOpen())
            database.close();
    }

    public ArrayList<Film> getAllFavorites() {
        ArrayList<Film> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        Film film;
        if (cursor.getCount() > 0) {
            do {
                film = new Film();
                film.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                film.setJudul(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                film.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
                film.setTahun(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
                film.setDetail(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                arrayList.add(film);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public boolean isExist(Film film) {
        database = dataBaseHelper.getReadableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_FAV + " WHERE " + _ID + "=" + film.getId();

        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public long insertFavorite(Film film) {
        ContentValues args = new ContentValues();
        args.put(_ID, film.getId());
        args.put(TITLE, film.getJudul());
        args.put(DATE, film.getTahun());
        args.put(IMAGE, film.getPoster());
        args.put(OVERVIEW, film.getDetail());
        return database.insert(DATABASE_TABLE, null, args);
    }

    public int deleteFavorite(int id) {
        return database.delete(TABLE_FAV, _ID + " = '" + id + "'", null);
    }
}

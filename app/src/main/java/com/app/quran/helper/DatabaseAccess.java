package com.app.quran.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.app.quran.realm.RealmHelper;
import com.app.quran.realm.model.Quran;

import java.util.ArrayList;

import io.realm.RealmList;

/**
 * Created by piyush on 9/1/2016.
 */
public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    private RealmHelper realmHelper;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    public DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
        realmHelper = new RealmHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }


    public RealmList<Quran> getAllQuranText() {
        if (realmHelper.getAllQuranList().size() == 0) {
            ArrayList<Quran> list = new ArrayList<Quran>();
            open();
            try {
                Cursor cursor;
                cursor = database.rawQuery("SELECT * FROM quran_text", null);
                int i = 0;
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    {
                        Quran quran = new Quran();
                        quran.setIndex(cursor.getInt(0));
                        quran.setSura(cursor.getInt(1));
                        quran.setAya(cursor.getInt(2));
                        quran.setText(cursor.getString(3));
                        list.add(quran);
                        cursor.moveToNext();
                    }
                }

            } catch (Exception e) {
                Log.e("Exception", "" + e);
            }
            realmHelper.addQuranList(list);
            close();
        }
        return realmHelper.getAllQuranList();

    }

}

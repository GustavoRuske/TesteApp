package com.example.ruske.testeapp;

/**
 * Created by Ruske on 06/11/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLControlador {

    private DBhelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLControlador(Context c) {
        ourcontext = c;
    }

    public SQLControlador iniciarBanco() throws SQLException {
        dbhelper = new DBhelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        dbhelper.close();
    }

    public void inserirDados(String name) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.ANOTACAO_TEXT, name);
        database.insert(DBhelper.TABLE_NAME, null, cv);
    }

    public Cursor lerDados() {
        String[] todasLasColumnas = new String[] {
                DBhelper.ANOTACAO_ID,
                DBhelper.ANOTACAO_TEXT
        };
        Cursor c = database.query(DBhelper.TABLE_NAME, todasLasColumnas, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public int atualizarDados(long memberID, String memberName) {
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DBhelper.ANOTACAO_TEXT, memberName);
        int i = database.update(DBhelper.TABLE_NAME, cvActualizar,
                DBhelper.ANOTACAO_ID + " = " + memberID, null);
        return i;
    }

    public void deleteData(long memberID) {
        database.delete(DBhelper.TABLE_NAME, DBhelper.ANOTACAO_ID + "="
                + memberID, null);
    }
}
package com.example.ruske.testeapp;

/**
 * Created by Ruske on 06/11/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    // Informa a tabela
    public static final String TABLE_NAME = "anotacao";
    public static final String ANOTACAO_ID = "_id";
    public static final String ANOTACAO_TEXT = "anotacao";

    // Informa os dados do banco
    static final String DB_NAME = "APP";
    static final int DB_VERSION = 1;

    // Informa os dados do banco
    private static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "(" + ANOTACAO_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ANOTACAO_TEXT + " TEXT NOT NULL);";

    public DBhelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
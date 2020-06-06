package com.example.tetrisrecords;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    private static final String GAMES_TABLE = "GAMES_TABLE";
    private static final String COLUMN_DATE = "DATE";
    private static final String COLUMN_SCORE = "SCORE";
    private static final String COLUMN_LEVEL = "LEVEL";
    private static final String COLUMN_ID = "ID";

    DataBase(@Nullable Context context) {
        super(context, "games.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + GAMES_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT, " + COLUMN_SCORE + " INT, " + COLUMN_LEVEL + " INT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    void addGame(GameStat game) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DATE, game.getDate());
        cv.put(COLUMN_SCORE, game.getScore());
        cv.put(COLUMN_LEVEL, game.getLevel());
        db.insert(GAMES_TABLE, null, cv);
        db.close();
    }
    void deleteGame(GameStat game) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = " DELETE FROM " + GAMES_TABLE + " WHERE " + COLUMN_ID + " = " +
                game.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
    }
    void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = " DELETE FROM " + GAMES_TABLE;
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
    }
    void editGame(GameStat game, String date, int score, int level) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = " UPDATE " + GAMES_TABLE + " SET " + COLUMN_DATE + " = " + date + ", "
                + COLUMN_SCORE + " = " + score + ", " + COLUMN_LEVEL + " = " + level +
                " WHERE " + COLUMN_ID + " = " + game.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
    }
    ArrayList<GameStat> getAll() {
        ArrayList<GameStat> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + GAMES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            do {
                int gameID = cursor.getInt(0);
                String date = cursor.getString(1);
                int score = cursor.getInt(2);
                int level = cursor.getInt(3);
                GameStat newGame = new GameStat();
                newGame.setDate(date);
                newGame.setScore(score);
                newGame.setLevel(level);
                newGame.setId(gameID);
                returnList.add(newGame);
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }
}
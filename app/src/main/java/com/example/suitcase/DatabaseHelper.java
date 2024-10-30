package com.example.suitcase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Define constants for the database name, table name, and database version.
    public static final String DB_NAME="ITEMS_INFO_DB";
    public static final String TABLE_NAME="ITEMS_INFO_TABLE";
    public static final int DB_VERSION = 1;

    // Define column names for the database table.
    public static final String COLUMN_ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";
    public static final String PURCHASED = "purchased";

    // Constructor for the DatabaseHelper class, which extends SQLiteOpenHelper.
    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    //This method is called when the database is created.
    //It defines the SQL query to create the "ITEMS_INFO_TABLE" table with the specified columns.
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT NOT NULL, " +
                PRICE + " TEXT NOT NULL, " +
                DESCRIPTION + " TEXT, " +
                IMAGE + " TEXT, " +
                PURCHASED + " INTEGER)";
        db.execSQL(sqlQuery);
    }
    //This method is called when the database needs to be upgraded, when the database version is increased.
    //It drops the existing table if it exists and calls onCreate to recreate the table.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sqlQuery);
        onCreate(db);
    }
    //This method allows executing custom SQL queries on the database.
    //It returns a Cursor that can be used to retrieve query results.
    public Cursor queryData(String sqlQuery){
        SQLiteDatabase database = getWritableDatabase();
        return database.rawQuery(sqlQuery, null);
    }
    //This method inserts a new row into the "ITEMS_INFO_TABLE" table with the provided item information.
    //It uses a prepared statement to bind the values to the columns and executes.
    // Return true if the insertion was successful, false otherwise.
    public Boolean insert(
            String name,
            double price,
            String description,
            String image,
            boolean purchased
    ) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO " + TABLE_NAME + " VALUES (NULL, ?, ?, ?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, name); //Bind the name into first parameter
        statement.bindDouble(2, price);
        statement.bindString(3, description);
        statement.bindString(4, image);
        statement.bindLong(5, purchased ? 1 : 0);
        long result = statement.executeInsert();
        database.close();
        return result != -1;
    }
    //This method retrieves a specific item from the table by its ID.
    //It constructs a SQL query with a WHERE clause to select the item with the given ID.
    public Cursor getElementById(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE "+ COLUMN_ID +"=?";
        return database.rawQuery(
                sqlQuery,
                new String[]{String.valueOf(id)}
        );
    }
    //his method retrieves all items from the table.
    //It constructs a SQL query to select all rows from the table.
    public Cursor getAll() {
        SQLiteDatabase database = getReadableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME;
        return database.rawQuery(sqlQuery, null);
    }
    //This method updates an existing item in the table based on its ID.
    //It constructs a ContentValues object with the updated values and uses the update method of SQLiteDatabase to perform the update.
    //It returns true if the update is successful, false otherwise.
    public Boolean update(
            int id,
            String name,
            double price,
            String description,
            String image,
            boolean purchased
    ) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(PRICE, price);
        cv.put(DESCRIPTION, description);
        cv.put(IMAGE, image);
        cv.put(PURCHASED, purchased);
        int result = database.update(TABLE_NAME, cv, COLUMN_ID+ "=?", new String[]{String.valueOf(id)});
        Log.d("Database helper:", "result: "+ result);
        database.close();
        return result != -1;
    }
    //This method deletes an item from the table based on its ID.
    //It uses the delete method of SQLiteDatabase to perform the deletion.
    public void delete(long id) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete(
                TABLE_NAME,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)});
    }
}
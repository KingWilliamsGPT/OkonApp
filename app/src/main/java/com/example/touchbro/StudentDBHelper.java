package com.example.touchbro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "studentDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "students";

    public StudentDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "matricNo TEXT," +
                "name TEXT," +
                "age INTEGER," +
                "department TEXT," +
                "profilePic BLOB)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert new student
    public long insertStudent(String matric, String name, int age, String dept, byte[] profilePic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("matricNo", matric);
        cv.put("name", name);
        cv.put("age", age);
        cv.put("department", dept);
        cv.put("profilePic", profilePic);
        return db.insert(TABLE_NAME, null, cv);
    }

    // Get all students
    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    // Search students
    public Cursor searchStudents(String query) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE name LIKE ? OR matricNo LIKE ?",
                new String[]{"%" + query + "%", "%" + query + "%"});
    }

    // Get student by ID
    public Cursor getStudentById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id=?", new String[]{String.valueOf(id)});
    }
}

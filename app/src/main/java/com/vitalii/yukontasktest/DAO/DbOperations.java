package com.vitalii.yukontasktest.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vitalii.yukontasktest.interfaces.People;
import com.vitalii.yukontasktest.objects.Student;
import com.vitalii.yukontasktest.objects.Teacher;

import java.util.ArrayList;

public class DbOperations extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "yukonTaskTest";
    private static final String CREATE_QUERY_STUDENT = "create table " + Student.TABLE_NAME + "("
            + Student.PersonEntity.KEY_ID + " integer primary key," + Student.PersonEntity.KEY_FNAME
            + " text," + Student.PersonEntity.KEY_LNAME + " text," + Student.PersonEntity.KEY_PHONE
            + " text," + Student.KEY_OPTION + " text" + ")";
    private static final String CREATE_QUERY_TEACHER = "create table " + Teacher.TABLE_NAME + "("
            + Teacher.PersonEntity.KEY_ID + " integer primary key," + Teacher.PersonEntity.KEY_FNAME
            + " text," + Teacher.PersonEntity.KEY_LNAME + " text," + Teacher.PersonEntity.KEY_PHONE
            + " text," + Teacher.KEY_OPTION + " text" + ")";

    DbOperations(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
        Log.d("DbOperation", "Database created...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY_STUDENT);
        Log.d("DbOperation", "Table for students created...");
        db.execSQL(CREATE_QUERY_TEACHER);
        Log.d("DbOperation", "Table for teachers created...");
    }

    public ArrayList<People> readData(SQLiteDatabase db, String tm) {
        ArrayList<People> listPeople = new ArrayList<>();
        Cursor cursor = db.query(tm, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(People.PersonEntity.KEY_ID);
            int fnameIndex = cursor.getColumnIndex(People.PersonEntity.KEY_FNAME);
            int lnameIndex = cursor.getColumnIndex(People.PersonEntity.KEY_LNAME);
            int phoneIndex = cursor.getColumnIndex(People.PersonEntity.KEY_PHONE);
            if (tm.equals(Student.TABLE_NAME)) {
                int optionIndex = cursor.getColumnIndex(Student.KEY_OPTION);
                do {
                    listPeople.add(new Student(Integer.parseInt(cursor.getString(idIndex)), cursor.getString(fnameIndex),
                            cursor.getString(lnameIndex), cursor.getString(phoneIndex),
                            cursor.getInt(optionIndex)));
                } while (cursor.moveToNext());
            } else if (tm.equals(Teacher.TABLE_NAME)) {
                int optionIndex = cursor.getColumnIndex(Teacher.KEY_OPTION);
                do {
                    listPeople.add(new Teacher(Integer.parseInt(cursor.getString(idIndex)), cursor.getString(fnameIndex),
                            cursor.getString(lnameIndex), cursor.getString(phoneIndex),
                            cursor.getString(optionIndex)));
                } while (cursor.moveToNext());
            }
        } else {
            cursor.close();
            db.close();
            return listPeople;
        }
        cursor.close();
        db.close();
        return listPeople;
    }

    public void addPerson(SQLiteDatabase db, People person) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(People.PersonEntity.KEY_FNAME, person.getfName());
        contentValues.put(People.PersonEntity.KEY_LNAME, person.getlName());
        contentValues.put(People.PersonEntity.KEY_PHONE, person.getPhone());
        if (person instanceof Student) {
            contentValues.put(Student.KEY_OPTION, ((Student) person).getCourse());
            db.insert(Student.TABLE_NAME, null, contentValues);
            Log.d("DbOperation", "One student inserted...");
        } else if (person instanceof Teacher) {
            contentValues.put(Teacher.KEY_OPTION, ((Teacher) person).getSubject());
            db.insert(Teacher.TABLE_NAME, null, contentValues);
            Log.d("DbOperation", "One teacher inserted...");
        }
        db.close();
    }

    public void editPerson(SQLiteDatabase db, People person) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(People.PersonEntity.KEY_ID, person.getId());
        contentValues.put(People.PersonEntity.KEY_FNAME, person.getfName());
        contentValues.put(People.PersonEntity.KEY_LNAME, person.getlName());
        contentValues.put(People.PersonEntity.KEY_PHONE, person.getPhone());
        if (person instanceof Student) {
            contentValues.put(Student.KEY_OPTION, ((Student) person).getCourse());
            db.replace(Student.TABLE_NAME, null, contentValues);
            Log.d("DbOperation", "One student edited...");
        } else if (person instanceof Teacher) {
            contentValues.put(Teacher.KEY_OPTION, ((Teacher) person).getSubject());
            db.replace(Teacher.TABLE_NAME, null, contentValues);
            Log.d("DbOperation", "One teacher edited...");
        }
        db.close();
    }

    public void deletePerson(SQLiteDatabase db, People person) {
        if (person instanceof Student) {
            db.delete(Student.TABLE_NAME, "_id = " + person.getId(), null);
            Log.d("DbOperation", "One student deleted...");
        } else if (person instanceof Teacher) {
            db.delete(Teacher.TABLE_NAME, "_id = " + person.getId(), null);
            Log.d("DbOperation", "One teacher deleted...");
        }
        db.close();
    }

    public void clearData(SQLiteDatabase db) {
        db.execSQL("drop table if exists " + Student.TABLE_NAME);
        db.execSQL("drop table if exists " + Teacher.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + Student.TABLE_NAME);
        db.execSQL("drop table if exists " + Teacher.TABLE_NAME);
        onCreate(db);
    }
}

package com.vitalii.yukontasktest.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

import com.vitalii.yukontasktest.objects.Student;
import com.vitalii.yukontasktest.objects.Teacher;

public class BackgroundTask extends AsyncTask<String, Void, String> {
    Context ctx;

    public BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        DbOperations dbOperations = new DbOperations(ctx);
        SQLiteDatabase db;
        db = dbOperations.getWritableDatabase();
        switch (method) {
            case "add_student": {
                if ((params[1].length() != 0 || params[2].length() != 0)
                        && params[4].length() != 0) {
                    try {
                        dbOperations.addPerson(db, new Student(params[1], params[2], params[3], Integer.parseInt(params[4])));
                    } catch (NumberFormatException e) {
                        dbOperations.close();
                        return "Failed inserted into DB, Course field is not in the correct format";
                    }
                    dbOperations.close();
                    return ("Student - " + params[1] + " " + params[2] + " inserted into DB");
                } else {
                    dbOperations.close();
                    return "Failed inserted into DB, one field is not in the correct format";
                }
            }
            case "add_teacher": {
                if ((params[1].length() != 0 || params[2].length() != 0)
                        && params[4].length() != 0) {
                    dbOperations.addPerson(db, new Teacher(params[1], params[2], params[3], params[4]));
                    dbOperations.close();
                    return ("Teacher - " + params[1] + " " + params[2] + " inserted into DB");
                } else {
                    dbOperations.close();
                    return "Failed inserted into DB, one field is not in the correct format";
                }
            }
            case "edit_student": {
                if ((params[2].length() != 0 || params[3].length() != 0)
                        && params[5].length() != 0) {
                    try {
                        dbOperations.editPerson(db, new Student(Integer.parseInt(params[1]), params[2], params[3], params[4], Integer.parseInt(params[5])));
                    } catch (NumberFormatException e) {
                        dbOperations.close();
                        return "Failed edit Student in DB, Course field is not in the correct format";
                    }
                    dbOperations.close();
                    return ("Student - " + params[2] + " " + params[3] + " edited in DB");
                } else {
                    dbOperations.close();
                    return "Failed edit Student in DB, one field is not in the correct format";
                }
            }
            case "edit_teacher": {
                if ((params[2].length() != 0 || params[3].length() != 0)
                        && params[5].length() != 0) {
                    dbOperations.editPerson(db, new Teacher(Integer.parseInt(params[1]), params[2], params[3], params[4], params[5]));
                    dbOperations.close();
                    return ("Teacher - " + params[2] + " " + params[3] + " edited in DB");
                } else {
                    dbOperations.close();
                    return "Failed edit Teacher in DB, one field is not in the correct format";
                }
            }
            case "delete_student": {
                dbOperations.deletePerson(db, new Student(Integer.parseInt(params[1]), "", "", "", 0));
                dbOperations.close();
                return ("Student - " + params[2] + " " + params[3] + " deleted in DB");
            }
            case "delete_teacher": {
                dbOperations.deletePerson(db, new Teacher(Integer.parseInt(params[1]), "", "", "", ""));
                dbOperations.close();
                return ("Teacher - " + params[2] + " " + params[3] + " deleted in DB");
            }
            case "clear_data": {
                dbOperations.clearData(db);
                dbOperations.close();
                return "All Data cleaned!";
            }
            default: {
                dbOperations.close();
                return null;
            }
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();
    }
}

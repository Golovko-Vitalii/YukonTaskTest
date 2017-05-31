package com.vitalii.yukontasktest.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.vitalii.yukontasktest.interfaces.People;

import java.util.ArrayList;

public class BackgroundTaskRead extends AsyncTask<String, Void, ArrayList<People>> {
    Context ctx;

    public BackgroundTaskRead(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected ArrayList<People> doInBackground(String... params) {
        String method = params[0];
        String tm = params[1];
        DbOperations dbOperations = new DbOperations(ctx);
        SQLiteDatabase db;
        db = dbOperations.getWritableDatabase();
        ArrayList<People> listPeople = new ArrayList<>();
        switch (method) {
            case "read_data": {
                listPeople = dbOperations.readData(db, tm);
                dbOperations.close();
                return listPeople;
            }
            default: {
                dbOperations.close();
                return listPeople;
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

}

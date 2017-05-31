package com.vitalii.yukontasktest.controllers;

import android.content.Context;

import com.vitalii.yukontasktest.DAO.BackgroundTaskRead;
import com.vitalii.yukontasktest.interfaces.People;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ReadInfo {
    Context ctx;
    String tm;

    public ReadInfo(Context ctx, String tm) {
        this.ctx = ctx;
        this.tm = tm;
    }

    public ArrayList<People> readData() {
        ArrayList<People> listPeople = new ArrayList<>();
        BackgroundTaskRead backgroundTaskRead = new BackgroundTaskRead(ctx);
        try {
            listPeople.addAll(backgroundTaskRead.execute("read_data", tm).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return listPeople;
    }
}

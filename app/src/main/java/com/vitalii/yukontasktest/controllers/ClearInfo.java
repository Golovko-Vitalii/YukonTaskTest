package com.vitalii.yukontasktest.controllers;

import android.content.Context;

import com.vitalii.yukontasktest.DAO.BackgroundTask;

public class ClearInfo {
    Context ctx;

    public ClearInfo(Context ctx) {
        this.ctx = ctx;
    }

    public void clearData() {
        BackgroundTask backgroundTask = new BackgroundTask(ctx);
        backgroundTask.execute("clear_data");
    }
}

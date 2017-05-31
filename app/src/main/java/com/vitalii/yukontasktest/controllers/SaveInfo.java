package com.vitalii.yukontasktest.controllers;

import android.content.Context;

import com.vitalii.yukontasktest.DAO.BackgroundTask;

public class SaveInfo {
    String textPerson, textFName, textLNname, textPhone, textOption;
    Context ctx;

    public SaveInfo(Context ctx, String textPerson, String textFName, String textLNname, String textPhone, String textOption) {
        this.ctx = ctx;
        this.textPerson = textPerson;
        this.textFName = textFName;
        this.textLNname = textLNname;
        this.textPhone = textPhone;
        this.textOption = textOption;
    }

    public void saveData() {
        BackgroundTask backgroundTask = new BackgroundTask(ctx);
        if (textPerson.equals("add_student")) {
            backgroundTask.execute("add_student", textFName, textLNname, textPhone, textOption);
        } else if (textPerson.equals("add_teacher")) {
            backgroundTask.execute("add_teacher", textFName, textLNname, textPhone, textOption);
        }
    }
}

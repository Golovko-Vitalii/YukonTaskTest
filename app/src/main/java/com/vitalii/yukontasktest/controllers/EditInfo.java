package com.vitalii.yukontasktest.controllers;

import android.content.Context;

import com.vitalii.yukontasktest.DAO.BackgroundTask;

public class EditInfo {
    String textId, textPerson, textFName, textLNname, textPhone, textOption;
    Context ctx;

    public EditInfo(Context ctx, String textPerson, String textId, String textFName, String textLNname, String textPhone, String textOption) {
        this.ctx = ctx;
        this.textPerson = textPerson;
        this.textId = textId;
        this.textFName = textFName;
        this.textLNname = textLNname;
        this.textPhone = textPhone;
        this.textOption = textOption;
    }

    public void editData() {
        BackgroundTask backgroundTask = new BackgroundTask(ctx);
        if (textPerson.equals("edit_student")) {
            backgroundTask.execute("edit_student", textId, textFName, textLNname, textPhone, textOption);
        } else if (textPerson.equals("edit_teacher")) {
            backgroundTask.execute("edit_teacher", textId, textFName, textLNname, textPhone, textOption);
        }
    }
}

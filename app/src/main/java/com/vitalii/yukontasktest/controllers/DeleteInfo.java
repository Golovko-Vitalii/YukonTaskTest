package com.vitalii.yukontasktest.controllers;

import android.content.Context;

import com.vitalii.yukontasktest.DAO.BackgroundTask;
import com.vitalii.yukontasktest.interfaces.People;
import com.vitalii.yukontasktest.objects.Student;
import com.vitalii.yukontasktest.objects.Teacher;

public class DeleteInfo {
    People person;
    Context ctx;

    public DeleteInfo(Context ctx, People person) {
        this.ctx = ctx;
        this.person = person;
    }

    public void deleteData() {
        BackgroundTask backgroundTask = new BackgroundTask(ctx);
        if (person instanceof Student) {
            backgroundTask.execute("delete_student", Integer.toString(person.getId()), person.getfName(), person.getlName(), "", "");
        } else if (person instanceof Teacher) {
            backgroundTask.execute("delete_teacher", Integer.toString(person.getId()), person.getfName(), person.getlName(), "", "");
        }
    }
}

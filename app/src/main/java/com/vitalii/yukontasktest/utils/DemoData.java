package com.vitalii.yukontasktest.utils;

import android.content.Context;

import com.vitalii.yukontasktest.controllers.SaveInfo;

public class DemoData {
    Context ctx;

    public DemoData(Context ctx) {
        this.ctx = ctx;
    }

    public void fillDemoData() {
        new SaveInfo(ctx, "add_teacher", "Aristotle", "", "None", "Philosophy").saveData();
        new SaveInfo(ctx, "add_student", "Hank", "Aaron", "985326549", "2").saveData();
        new SaveInfo(ctx, "add_teacher", "Isaac", "Newton", "None", "Physics").saveData();
        new SaveInfo(ctx, "add_student", "Russell", "Baker", "954126542", "1").saveData();
        new SaveInfo(ctx, "add_student", "Andy", "Barclay", "982265588", "1").saveData();
        new SaveInfo(ctx, "add_student", "Dave", "Barry", "912513548", "4").saveData();
        new SaveInfo(ctx, "add_teacher", "Adam", "Smith", "None", "Economics").saveData();
        new SaveInfo(ctx, "add_teacher", "Leonhard", "Euler", "None", "Mathematics").saveData();
        new SaveInfo(ctx, "add_student", "Jon", "White", "956823588", "1").saveData();
        new SaveInfo(ctx, "add_student", "Kim", "Chen Ir", "920206485", "2").saveData();
        new SaveInfo(ctx, "add_student", "Alex", "Black", "918625628", "3").saveData();
        new SaveInfo(ctx, "add_teacher", "Leo", "Tolstoy", "None", "Literature").saveData();
    }

    ;
}

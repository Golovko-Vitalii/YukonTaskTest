package com.vitalii.yukontasktest.objects;

import com.vitalii.yukontasktest.interfaces.People;

public class Teacher extends People {
    private String subject;
    public static final String TABLE_NAME = "teacher_table";
    public static final String KEY_OPTION = "subject";

    public Teacher() {
    }

    public Teacher(int id, String fName, String lName, String phone, String subject) {
        super(id, fName, lName, phone);
        this.subject = subject;
    }

    public Teacher(String fName, String lName, String phone, String subject) {
        super(fName, lName, phone);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
}

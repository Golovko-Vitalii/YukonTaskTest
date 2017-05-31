package com.vitalii.yukontasktest.objects;

import com.vitalii.yukontasktest.interfaces.People;

public class Student extends People {
    private int course;
    public static final String TABLE_NAME = "student_table";
    public static final String KEY_OPTION = "course";

    public Student() {
    }

    public Student(int id, String fName, String lName, String phone, int course) {
        super(id, fName, lName, phone);
        this.course = course;
    }

    public Student(String fName, String lName, String phone, int course) {
        super(fName, lName, phone);
        this.course = course;
    }

    public int getCourse() {
        return course;
    }
}

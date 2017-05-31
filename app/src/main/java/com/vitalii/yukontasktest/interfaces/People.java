package com.vitalii.yukontasktest.interfaces;

public abstract class People {
    private int id;
    private String fName;
    private String lName;
    private String phone;

    public static abstract class PersonEntity {
        public static final String KEY_ID = "_id";
        public static final String KEY_FNAME = "fname";
        public static final String KEY_LNAME = "lname";
        public static final String KEY_PHONE = "phone";
    }

    public People() {
    }

    public People(int id, String fName, String lName, String phone) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
    }

    public People(String fName, String lName, String phone) {
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getPhone() {
        return phone;
    }

    public int getId() {
        return id;
    }
}

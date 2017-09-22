package com.example.akil.onusondhan;

/**
 * Created by Akil on 9/7/2017.
 */

class Found {
    public String foundName;
    public String wght;
    public String mark;
    public String desc;
    public String hght;
    public int age;

    public String getFoundName() {
        return foundName;
    }

    public String getWght() {
        return wght;
    }

    public String getMark() {
        return mark;
    }

    public String getDesc() {
        return desc;
    }

    public String getHght() {
        return hght;
    }

    public int getAge() {
        return age;
    }

    public Found(String foundName, String wght, String mark, String desc, String hght, int age) {
        this.foundName = foundName;
        this.wght = wght;
        this.mark = mark;
        this.desc = desc;
        this.hght = hght;
        this.age = age;
    }
}

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
    public String path;
    public String numbr;
    public String fndplac;
    public String fnddat;
    public String skcolor;
    public int age;

    public Found() {
    }

    public String getNumbr() {
        return numbr;
    }

    public String getFndplac() {
        return fndplac;
    }

    public String getFnddat() {
        return fnddat;
    }

    public String getSkcolor() {
        return skcolor;
    }

    public String getPath() {
        return path;
    }

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

    public Found(String foundName, String wght, String mark, String desc, String hght, int age,String path,String skcolor,String fnddat,String fndplac,String numbr) {
        this.foundName = foundName;
        this.wght = wght;
        this.mark = mark;
        this.desc = desc;
        this.hght = hght;
        this.age = age;
        this.path=path;
        this.fnddat=fnddat;
        this.fndplac=fndplac;
        this.skcolor=skcolor;
        this.numbr=numbr;
    }

}

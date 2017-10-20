package com.example.akil.onusondhan;

/**
 * Created by Valociraptor on 10/19/2017.
 */
class Missing {
    public String missingName;
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

    public Missing() {
    }
    public Missing(String missingName, String wght, String mark, String desc, String hght, int age,String path,String skcolor,String fnddat,String fndplac,String numbr) {
        this.missingName = missingName;
        this.wght = wght;
        this.mark = mark;
        this.desc = desc;
        this.hght = hght;
        this.path = path;
        this.age = age;
        this.fnddat=fnddat;
        this.fndplac=fndplac;
        this.skcolor=skcolor;
        this.numbr=numbr;
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

    public String getMissingName() {
        return missingName;
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

    public String getPath() {
        return path;
    }

    public int getAge() {
        return age;
    }


}

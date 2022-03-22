package com.example.stlukesmedicalcentre.Hospital;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbDoctor")
public class Doctor {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String dept;

    private String time;

    @Override
    public String toString() {
        String s="\nDoctor Name : ";
        s+=name;
        s+='\n';
        s+='\n';
        s+="Department : ";
        s+=dept;
        s+='\n';
        s+='\n';
        s+="D&T: ";
        s+=time;
        s+='\n';
        s+='\n';
        return s;
        //return "Doctor " + ", name= " + name  + ", dept= " + dept + ", time= " + time ;
    }

    public Doctor(int id, String name, String dept, String time) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.time = time;
    }



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getTime() {
        return time;
    }
}

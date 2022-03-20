package com.example.stlukesmedicalcentre.Hospital;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vet_data")
public class Doctor {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String trade_name;

    private String composition;

    private String trade_dose;

    private String company;

    private String generic_name;

    private String comments;

    private String pack_size;

    private String details;


    public Doctor(String trade_name, String composition, String trade_dose, String company, String generic_name, String comments, String pack_size, String details) {
        this.trade_name = trade_name;
        this.composition = composition;
        this.trade_dose = trade_dose;
        this.company = company;
        this.generic_name = generic_name;
        this.comments = comments;
        this.pack_size = pack_size;
        this.details = details;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTrade_name() {
        return trade_name;
    }

    public String getComposition() {
        return composition;
    }

    public String getTrade_dose() {
        return trade_dose;
    }

    public String getCompany() {
        return company;
    }

    public String getGeneric_name() {
        return generic_name;
    }

    public String getComments() {
        return comments;
    }

    public String getPack_size() {
        return pack_size;
    }

    public String getDetails() {
        return details;
    }

}

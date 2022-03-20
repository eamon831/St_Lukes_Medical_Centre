package com.example.stlukesmedicalcentre.Hospital;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private doc_dao vet_dao;
    private LiveData<List<Doctor>> allData,searched_data;

    public Repository(Application application)
    {
        Doc_Database database=Doc_Database.getDatabase(application);
        vet_dao = database.vet_dao();
        allData=vet_dao.getAlldata();
    }

    public void insert(Doctor vd)
    {
           Doc_Database.databaseWriteExecutor.execute(() ->{
               vet_dao.insert(vd);
           });
    }

    public void update(Doctor vd)
    {
        Doc_Database.databaseWriteExecutor.execute(() ->{
            vet_dao.update(vd);
        });

    }

    public void delete(Doctor vd)
    {
        Doc_Database.databaseWriteExecutor.execute(() ->{
            vet_dao.delete(vd);
        });
    }

    public void deleteall()
    {
        Doc_Database.databaseWriteExecutor.execute(() ->{
            vet_dao.deleteAll();
        });
    }

    public  LiveData<List<Doctor>> getAllData()
    {
        return allData;
    }
    public  LiveData<List<Doctor>> getSearched_data(String search)
    {
        return vet_dao.getSearcheddata(search);
    }


}

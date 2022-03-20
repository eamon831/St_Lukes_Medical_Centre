package com.example.stlukesmedicalcentre;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.stlukesmedicalcentre.Hospital.Doctor;
import com.example.stlukesmedicalcentre.Hospital.Repository;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Doctor>> allData,searched_data;
    public ViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
        allData=repository.getAllData();
    }

    LiveData<List<Doctor>> getAllData()
    {
        return allData;
    }
    LiveData<List<Doctor>> getSearched_data(String search)
    { return repository.getSearched_data(search); }
    public void update(Doctor vd)
    {
        repository.update(vd);
    }

    public void delete(Doctor vd)
    {
        repository.delete(vd);
    }

    public void insert(Doctor vd)
    {
        repository.insert(vd);
    }

    public void deleteall()
    {
        repository.deleteall();
    }
}

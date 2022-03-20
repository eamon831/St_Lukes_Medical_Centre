package com.example.stlukesmedicalcentre.Hospital;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface doc_dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Doctor vd);

    @Update
    void update(Doctor vd);

    @Delete
    void delete(Doctor vd);

    @Query("DELETE FROM vet_data")
    void deleteAll();

    @Query("SELECT * FROM vet_data ORDER BY id DESC")
    LiveData<List<Doctor>> getAlldata();

    @Query("SELECT * FROM vet_data WHERE trade_name LIKE :search OR generic_name LIKE :search")
    LiveData<List<Doctor>> getSearcheddata(String search);
}


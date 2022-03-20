package com.example.stlukesmedicalcentre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.stlukesmedicalcentre.Hospital.Doctor;

import java.util.List;


public class Booking extends AppCompatActivity {
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        viewModel=new ViewModelProvider(this).get(ViewModel.class);

       // List<Doctor> list=  viewModel.getalllist();




        RecyclerView recyclerView = findViewById(R.id.doctorlist);
        final DoctorAdapter adapter = new DoctorAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        viewModel.getAllData().observe(this, new Observer<List<Doctor>>() {
            @Override
            public void onChanged(List<Doctor> veterinary_dataModels) {
                adapter.setWords(veterinary_dataModels);
            }
        });



//



    }
}
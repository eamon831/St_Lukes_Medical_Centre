package com.example.stlukesmedicalcentre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Gallery;
import android.widget.TextView;

public class Main_Menu extends AppCompatActivity {
    TextView regi,main,booked,about,web,gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        regi=findViewById(R.id.regi);
        main=findViewById(R.id.main);
        booked=findViewById(R.id.booked);
        about=findViewById(R.id.about);
        web=findViewById(R.id.web);
        //gallery=findViewById(R.id.gallery);


        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main_Menu.this,Registration.class));
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main_Menu.this,Booking.class));
            }
        });
        booked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main_Menu.this,Single_User.class));

            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main_Menu.this,Contact_us.class));
            }
        });
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main_Menu.this,web.class));
            }
        });
        
    }
}
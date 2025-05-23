package com.example.shreya.form_ex;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Adapter.BookListAdapter;
import Adapter.Studentlistadapter;
import Model.BookListModel;
import Model.Studentlistmodel;

public class Studentlistactivity extends AppCompatActivity {

    DataBaseHelperClass databaseHelper;
    List<Studentlistmodel> studentlistmodelList;

    RecyclerView recylerView1 ;
    Studentlistadapter studentlistadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlistactivity);

        recylerView1 = findViewById(R.id.recylerView1);



        databaseHelper = new DataBaseHelperClass(this);


        recylerView1 = (RecyclerView) findViewById(R.id.recylerView1);
        recylerView1.setHasFixedSize(true);
        recylerView1.setLayoutManager(new LinearLayoutManager(Studentlistactivity.this));

        studentlistmodelList = new ArrayList<>();
        studentlistmodelList.clear();
        studentlistmodelList = databaseHelper.StudentListData();


        studentlistadapter = new Studentlistadapter(this, studentlistmodelList);
        recylerView1.setAdapter(studentlistadapter);


    }

}

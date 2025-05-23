package com.example.shreya.form_ex;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Adapter.BookListAdapter;
import Adapter.searchstudentbooksadapter;
import Model.BookListModel;

public class searchbookactivity extends AppCompatActivity {
    Button btnsearch;
    TextView txtsearch;
    AutoCompleteTextView autotxt;


    DataBaseHelperClass databaseHelper;
    List<BookListModel> bookListModelList;

    RecyclerView recylerView1 ;
    searchstudentbooksadapter searchstudentbooksadaptersclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbookactivity);


        btnsearch=(Button)findViewById(R.id.btnsearch);
        txtsearch=(TextView)findViewById(R.id.txtsearch);
        autotxt=(AutoCompleteTextView)findViewById(R.id.autotxt);
        recylerView1 = findViewById(R.id.recylerView1);




        databaseHelper = new DataBaseHelperClass(this);


        recylerView1 = (RecyclerView) findViewById(R.id.recylerView1);
        recylerView1.setHasFixedSize(true);
        recylerView1.setLayoutManager(new LinearLayoutManager(searchbookactivity.this));



        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookListModelList = new ArrayList<>();
                bookListModelList.clear();
                bookListModelList = databaseHelper.BookListDataByStd(autotxt.getText().toString());


                searchstudentbooksadaptersclass = new searchstudentbooksadapter(searchbookactivity.this, bookListModelList);
                recylerView1.setAdapter(searchstudentbooksadaptersclass);

            }
        });

    }


}

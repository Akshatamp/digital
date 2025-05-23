package com.example.shreya.form_ex;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Adapter.searchstudentbooksadapter;
import Model.BookListModel;

public class searchstudentbooks extends AppCompatActivity {

    private DataBaseHelperClass databaseHelper;
    private List<BookListModel> bookListModelList;

    private RecyclerView recyclerView1;
    private searchstudentbooksadapter searchStudentBooksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchstudentbooks);

        // Initialize RecyclerView
        recyclerView1 = findViewById(R.id.recylerView1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Database
        databaseHelper = new DataBaseHelperClass(this);

        // Fetch book list data
        bookListModelList = databaseHelper.BookListData();  // FIXED method name typo

        // Set adapter
        searchStudentBooksAdapter = new searchstudentbooksadapter(this, bookListModelList);
        recyclerView1.setAdapter(searchStudentBooksAdapter);
    }
}

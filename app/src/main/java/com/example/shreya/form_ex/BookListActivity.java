package com.example.shreya.form_ex;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Adapter.BookListAdapter;
import Model.BookListModel;

public class BookListActivity extends AppCompatActivity {

    private DataBaseHelperClass databaseHelper;
    private List<BookListModel> bookListModelList;

    private RecyclerView recyclerView1;
    private BookListAdapter bookListAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        // Initialize the RecyclerView
        recyclerView1 = findViewById(R.id.recylerView1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the database helper
        databaseHelper = new DataBaseHelperClass(this);

        // Load book data
        bookListModelList = databaseHelper.BookListData(); // FIXED METHOD NAME

        // Set adapter
        bookListAdapter = new BookListAdapter(this, bookListModelList);
        recyclerView1.setAdapter(bookListAdapter);
    }
}

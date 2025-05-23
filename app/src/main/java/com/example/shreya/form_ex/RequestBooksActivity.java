package com.example.shreya.form_ex;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RequestBooksActivity extends AppCompatActivity {

    EditText editTextBookName, editTextAuthorName;
    Button btnSubmitRequest;
    DataBaseHelperClass databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_books);

        editTextBookName = findViewById(R.id.editTextBookName);
        editTextAuthorName = findViewById(R.id.editTextAuthorName);
        btnSubmitRequest = findViewById(R.id.btnSubmitRequest);

        databaseHelper = new DataBaseHelperClass(this);

        btnSubmitRequest.setOnClickListener(v -> {
            String bookName = editTextBookName.getText().toString().trim();
            String authorName = editTextAuthorName.getText().toString().trim();

            if (TextUtils.isEmpty(bookName) || TextUtils.isEmpty(authorName)) {
                Toast.makeText(RequestBooksActivity.this, "Please enter both book and author name", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean inserted = databaseHelper.insertBookRequest(bookName, authorName);

            if (inserted) {
                Toast.makeText(RequestBooksActivity.this, "Request submitted successfully!", Toast.LENGTH_LONG).show();
                editTextBookName.setText("");
                editTextAuthorName.setText("");
            } else {
                Toast.makeText(RequestBooksActivity.this, "Failed to submit request. Try again.", Toast.LENGTH_LONG).show();
            }
        });
    }
}

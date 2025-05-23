package com.example.shreya.form_ex;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class addbookdetailsactivity extends AppCompatActivity {

    EditText editbname, editbprice, editbdsc, editautname, editQty;
    Button btnAdd, btnView;

    DataBaseHelperClass dataBaseHelperClass;

    String bookId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbookdetailsactivity);

        editautname = findViewById(R.id.editautname);
        editbname = findViewById(R.id.editbname);
        editbdsc = findViewById(R.id.editbdsc);
        editQty = findViewById(R.id.editQty);
        editbprice = findViewById(R.id.editbprice);

        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);

        dataBaseHelperClass = new DataBaseHelperClass(this);

        // Get extras
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("BookId")) {
            // Editing existing book
            bookId = bundle.getString("BookId");

            if (bookId != null) {
                Cursor cursor = dataBaseHelperClass.editBookDetails(bookId);
                if (cursor != null && cursor.moveToFirst()) {
                    editbname.setText(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelperClass.COL_BOOK_NAME)));
                    editQty.setText(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelperClass.COL_QTY)));
                    editbdsc.setText(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelperClass.COL_BOOK_DES)));
                    editautname.setText(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelperClass.COL_BOOK_AUTH)));
                    editbprice.setText(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelperClass.COL_PRICE)));

                    btnAdd.setText("Update");
                    cursor.close();
                }
            }
        } else {
            // Adding new book
            btnAdd.setText("Add");
        }

        btnView.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), BookListActivity.class));
        });


        btnAdd.setOnClickListener(v -> {
            String bookName = editbname.getText().toString().trim();
            String bookPrice = editbprice.getText().toString().trim();
            String qty = editQty.getText().toString().trim();
            String description = editbdsc.getText().toString().trim();
            String authorName = editautname.getText().toString().trim();

            // Validation
            if (bookName.isEmpty()) {
                showToast("Please enter book name");
            } else if (bookPrice.isEmpty()) {
                showToast("Please enter price of book");
            } else if (qty.isEmpty()) {
                showToast("Please enter quantity of books");
            } else if (description.isEmpty()) {
                showToast("Please enter description of book");
            } else if (authorName.isEmpty()) {
                showToast("Please enter author name of book");
            } else {
                if (btnAdd.getText().toString().equals("Add")) {
                    // Add new book
                    boolean isAdded = dataBaseHelperClass.addBookDetails(
                            bookName, authorName, qty, description, "Available", bookPrice);

                    if (isAdded) {
                        clearFields();
                        showToast("Book details added successfully");
                    } else {
                        showToast("Failed to add book details. Try again.");
                    }
                } else {
                    // Update existing book
                    boolean isUpdated = dataBaseHelperClass.updateBookDetails(
                            bookId, bookName, authorName, qty, description, "Available", bookPrice);

                    if (isUpdated) {
                        showToast("Book details updated successfully");
                        finish(); // close activity
                    } else {
                        showToast("Failed to update book details. Try again.");
                    }
                }
            }
        });
    }

    private void clearFields() {
        editbname.setText("");
        editbprice.setText("");
        editQty.setText("");
        editbdsc.setText("");
        editautname.setText("");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

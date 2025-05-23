package com.example.shreya.form_ex;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Model.BookListModel;

public class BorrowedBooksActivity extends AppCompatActivity {

    private ListView listViewBorrowedBooks;
    private List<BookListModel> availableBooks;
    private DataBaseHelperClass databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowed_books);

        listViewBorrowedBooks = findViewById(R.id.listViewBorrowedBooks);
        databaseHelper = new DataBaseHelperClass(this);

        // Fetch available books (make sure this method returns List<BookListModel>)
        availableBooks = databaseHelper.getAvailableBooks();

        // Extract book names for display
        List<String> bookNames = new ArrayList<>();
        for (BookListModel book : availableBooks) {
            bookNames.add(book.getBookName());
        }

        // Setup adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                bookNames
        );
        listViewBorrowedBooks.setAdapter(adapter);

        // Item click listener for borrow action
        listViewBorrowedBooks.setOnItemClickListener((parent, view, position, id) -> {
            BookListModel selectedBook = availableBooks.get(position);
            showBorrowOptionsDialog(selectedBook);
        });
    }

    private void showBorrowOptionsDialog(BookListModel selectedBook) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Borrow Options for\n" + selectedBook.getBookName());

        String[] durations = {"7", "14", "30"}; // days to borrow

        builder.setSingleChoiceItems(durations, -1, (dialog, which) -> {
            int selectedDays = Integer.parseInt(durations[which]);

            // Current date as borrow date
            Date now = new Date();
            String borrowDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(now);

            // Calculate due date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            calendar.add(Calendar.DAY_OF_YEAR, selectedDays);
            String dueDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime());

            // Call DB helper method to insert borrowed book
            boolean success = databaseHelper.BorrowedBook(
                    selectedBook.getBookId(),
                    selectedBook.getBookName(),
                    borrowDate,
                    dueDate,
                    selectedDays
            );

            if (success) {
                Toast.makeText(BorrowedBooksActivity.this,
                        "Borrowed Successfully!\nDue Date: " + dueDate,
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(BorrowedBooksActivity.this,
                        "Failed to borrow book", Toast.LENGTH_SHORT).show();
            }

            dialog.dismiss();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.show();
    }
}

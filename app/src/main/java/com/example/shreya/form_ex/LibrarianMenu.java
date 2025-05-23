package com.example.shreya.form_ex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.widget.Button;

public class LibrarianMenu extends AppCompatActivity {

    CardView cardViewAddBooks, cardviewstudentlist, cardViewUpdateProfile, cardViewBorrowedBooks;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_menu);

        // Initialize cards
        cardViewAddBooks = findViewById(R.id.cardViewAddBooks);
        cardviewstudentlist = findViewById(R.id.cardviewstudentlist);
        cardViewUpdateProfile = findViewById(R.id.cardViewUpdateProfile);   // new
        cardViewBorrowedBooks = findViewById(R.id.cardViewBorrowedBooks);   // new
        btnLogout = findViewById(R.id.btnLogout);

        // Add Book
        cardViewAddBooks.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), addbookdetailsactivity.class)));

        // Student List
        cardviewstudentlist.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), Studentlistactivity.class)));

        // Update Profile
        cardViewUpdateProfile.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), UpdateProfileActivity.class)));

        // Borrowed Books
        cardViewBorrowedBooks.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), BorrowedBooksActivity.class)));

        // Logout
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(LibrarianMenu.this, LoginPage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}

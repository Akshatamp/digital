package com.example.shreya.form_ex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class studentmenu_activity extends AppCompatActivity {
    CardView cvup, cvbb, cvrb, cvsb;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentmenu_activity);

        // Initialize CardViews
        cvup = findViewById(R.id.cvup);
        cvbb = findViewById(R.id.cvbb);
        cvrb = findViewById(R.id.cvrb);
        cvsb = findViewById(R.id.cvsb);
        btnLogout = findViewById(R.id.btnLogout);

        // Set click listener for Search Books
        cvsb.setOnClickListener(v ->
                startActivity(new Intent(studentmenu_activity.this, searchbookactivity.class))
        );

        // Optional: Add actions for other card views if needed
        // Example for Update Profile:
        cvup.setOnClickListener(v ->
                startActivity(new Intent(studentmenu_activity.this, UpdateProfileActivity.class))
        );

        // Example for Borrowed Books:
        cvbb.setOnClickListener(v ->
                startActivity(new Intent(studentmenu_activity.this, BorrowedBooksActivity.class))
        );

        // Example for Request Books:
        cvrb.setOnClickListener(v ->
                startActivity(new Intent(studentmenu_activity.this, RequestBooksActivity.class))
        );
        btnLogout.setOnClickListener(v -> {
            // Optional: Clear login session or preferences if any

            // Redirect to login activity
            Intent intent = new Intent(studentmenu_activity.this, LoginPage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clears activity stack
            startActivity(intent);
            finish();
        });
    }
}

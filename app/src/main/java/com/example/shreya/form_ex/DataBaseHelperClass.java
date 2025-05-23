package com.example.shreya.form_ex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;

import Model.BookListModel;
import Model.Studentlistmodel;

public class DataBaseHelperClass extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Users.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_BOOK_REQUESTS = "book_requests";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_BOOK_NAME = "book_name";
    private static final String COLUMN_AUTHOR_NAME = "author_name";

    // Table: Users
    public static final String TABLE_USERS = "TblUsers";
    public static final String COL_STUDENT_ID = "StudentId";
    public static final String COL_FIRST_NAME = "FirstName";
    public static final String COL_LAST_NAME = "LastName";
    public static final String COL_GENDER = "Gender";
    public static final String COL_USN_NUMBER = "USNNumber";
    public static final String COL_DEPARTMENT = "Department";
    public static final String COL_EMAIL_ID = "EmailId";
    public static final String COL_PASSWORD = "Password";
    public static final String COL_ADDRESS = "Address";

    // Table: Books
    public static final String TABLE_BOOKS = "TblBooks";
    public static final String COL_BOOK_ID = "BookId";
    public static final String COL_BOOK_NAME = "BookName";
    public static final String COL_BOOK_AUTH = "BookAuth";
    public static final String COL_QTY = "Qty";
    public static final String COL_PRICE = "Price";
    public static final String COL_BOOK_DES = "BookDes";
    public static final String COL_BOOK_STATUS = "BookStatus";

    public static final String TABLE_BORROWED = "TblBorrowedBooks";
    public static final String COL_BORROW_ID = "borrow_id";
    //public static final String COL_BOOK_ID = "book_id";
    //public static final String COL_BOOK_NAME = "book_name";
    public static final String COL_BORROW_DATE = "borrow_date";
    public static final String COL_DUE_DATE = "due_date";
    public static final String COL_DUE_DAYS = "due_days";


    private final Context context;

    public DataBaseHelperClass(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COL_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_FIRST_NAME + " TEXT, " +
                COL_LAST_NAME + " TEXT, " +
                COL_GENDER + " TEXT, " +
                COL_USN_NUMBER + " TEXT, " +
                COL_DEPARTMENT + " TEXT, " +
                COL_EMAIL_ID + " TEXT UNIQUE, " +
                COL_PASSWORD + " TEXT, " +
                COL_ADDRESS + " TEXT)";

        String createBooksTable = "CREATE TABLE " + TABLE_BOOKS + " (" +
                COL_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_BOOK_NAME + " TEXT, " +
                COL_BOOK_AUTH + " TEXT, " +
                COL_QTY + " TEXT, " +
                COL_PRICE + " TEXT, " +
                COL_BOOK_DES + " TEXT, " +
                COL_BOOK_STATUS + " TEXT)";

        String CREATE_BOOK_REQUESTS_TABLE = "CREATE TABLE " + TABLE_BOOK_REQUESTS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_BOOK_NAME + " TEXT, "
                + COLUMN_AUTHOR_NAME + " TEXT)";
        db.execSQL(CREATE_BOOK_REQUESTS_TABLE);

        String createBorrowedBooksTable = "CREATE TABLE " + TABLE_BORROWED + " (" +
                COL_BORROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_BOOK_ID + " TEXT, " +
                COL_BOOK_NAME + " TEXT, " +
                COL_BORROW_DATE + " TEXT, " +
                COL_DUE_DATE + " TEXT, " +
                COL_DUE_DAYS + " INTEGER)";
        db.execSQL(createBorrowedBooksTable);

        db.execSQL(createUsersTable);
        db.execSQL(createBooksTable);
        db.execSQL(CREATE_BOOK_REQUESTS_TABLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.disableWriteAheadLogging();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK_REQUESTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BORROWED);
        onCreate(db);
    }


    // Register new student
    public boolean StudentRegister(String firstName, String lastName, String usnNumber, String department,
                                   String emailId, String password, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_FIRST_NAME, firstName);
        values.put(COL_LAST_NAME, lastName);
        values.put(COL_USN_NUMBER, usnNumber);
        values.put(COL_DEPARTMENT, department);
        values.put(COL_EMAIL_ID, emailId);
        values.put(COL_PASSWORD, password);
        values.put(COL_ADDRESS, address);

        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }
    public boolean BorrowedBook(String bookId, String bookName, String borrowDate, String dueDate, int dueDays) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("BookId", bookId);
            values.put("BookName", bookName);
            values.put("BorrowDate", borrowDate);
            values.put("DueDate", dueDate);
            values.put("DueDays", dueDays);

            long result = db.insert("borrowed_books", null, values);

            if (result == -1) {
                Log.e("DB_ERROR", "Insert failed for borrowed_books");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            Log.e("DB_EXCEPTION", "Exception in BorrowedBook: " + e.getMessage());
            return false;
        }
    }



    // Add new book details
    public boolean AddBookDetails(String bookName, String bookAuth, String qty, String bookDes, String bookStatus, String bookPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_BOOK_NAME, bookName);
        values.put(COL_BOOK_AUTH, bookAuth);
        values.put(COL_QTY, qty);
        values.put(COL_BOOK_DES, bookDes);
        values.put(COL_BOOK_STATUS, bookStatus);
        values.put(COL_PRICE, bookPrice);

        long result = db.insert(TABLE_BOOKS, null, values);
        return result != -1;
    }

    // Show a toast message
    public void ToastMsg(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    // Check if email exists
    public Cursor checkEmailId(String emailId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE " + COL_EMAIL_ID + " = ?", new String[]{emailId});
        if (cursor != null) cursor.moveToFirst();
        return cursor;
    }

    // Student login check
    public Cursor StudentLogin(String emailId, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE " + COL_EMAIL_ID + " = ? AND " + COL_PASSWORD + " = ?",
                new String[]{emailId, password});
        if (cursor != null) cursor.moveToFirst();
        return cursor;
    }

    // Get details of a book by its ID for editing
    public Cursor EditBookDetails(String bookId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_BOOKS + " WHERE " + COL_BOOK_ID + " = ?", new String[]{bookId});
        if (cursor != null) cursor.moveToFirst();
        return cursor;
    }

    // Update book details by BookId
    public boolean UpdateBookDetails(String bookId, String bookName, String bookAuth, String qty, String bookDes, String bookStatus, String bookPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_BOOK_NAME, bookName);
        values.put(COL_BOOK_AUTH, bookAuth);
        values.put(COL_QTY, qty);
        values.put(COL_BOOK_DES, bookDes);
        values.put(COL_BOOK_STATUS, bookStatus);
        values.put(COL_PRICE, bookPrice);

        int result = db.update(TABLE_BOOKS, values, COL_BOOK_ID + " = ?", new String[]{bookId});
        return result > 0;
    }
    public Cursor getStudentByUSN(String usn) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM students WHERE usn = ?", new String[]{usn});
    }

    public boolean updateStudentProfile(String regno, String fname, String lname, String gender, String dept, String email, String password, String mobile, String address) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", fname);
        contentValues.put("lastName", lname);
        contentValues.put("gender", gender);
        contentValues.put("department", dept);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("mobile", mobile);
        contentValues.put("address", address);

        // regno used as primary key or unique key to identify the student
        int result = db.update("TblUsers", contentValues, "regno = ?", new String[]{regno});

        return result > 0;  // returns true if at least 1 row updated
    }




    // Duplicate method (optional to remove one if you want)
    public Cursor editBookDetails(String bookId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_BOOKS + " WHERE " + COL_BOOK_ID + "=?";
        return db.rawQuery(query, new String[]{bookId});
    }

    public boolean insertBookRequest(String bookName, String authorName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BOOK_NAME, bookName);
        values.put(COLUMN_AUTHOR_NAME, authorName);

        long result = db.insert(TABLE_BOOK_REQUESTS, null, values);
        db.close();
        return result != -1; // returns true if insert was successful
    }




    public boolean addBookDetails(String bookName, String authorName, String qty, String description, String status, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_BOOK_NAME, bookName);
        cv.put(COL_BOOK_AUTH, authorName);
        cv.put(COL_QTY, qty);
        cv.put(COL_BOOK_DES, description);
        cv.put(COL_BOOK_STATUS, status); // Fixed here
        cv.put(COL_PRICE, price);

        long result = db.insert(TABLE_BOOKS, null, cv);
        return result != -1;
    }

    public boolean updateBookDetails(String bookId, String bookName, String authorName, String qty, String description, String status, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_BOOK_NAME, bookName);
        cv.put(COL_BOOK_AUTH, authorName);
        cv.put(COL_QTY, qty);
        cv.put(COL_BOOK_DES, description);
        cv.put(COL_BOOK_STATUS, status); // Fixed here
        cv.put(COL_PRICE, price);

        int rows = db.update(TABLE_BOOKS, cv, COL_BOOK_ID + "=?", new String[]{bookId});
        return rows > 0;
    }

    // Get list of all books
    public List<BookListModel> BookListData() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<BookListModel> bookList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_BOOKS, null);
            if (cursor.moveToFirst()) {
                do {
                    BookListModel model = new BookListModel();
                    model.setBookId(cursor.getString(cursor.getColumnIndexOrThrow(COL_BOOK_ID)));
                    model.setBookName(cursor.getString(cursor.getColumnIndexOrThrow(COL_BOOK_NAME)));
                    model.setQty(cursor.getString(cursor.getColumnIndexOrThrow(COL_QTY)));
                    model.setBookDes(cursor.getString(cursor.getColumnIndexOrThrow(COL_BOOK_DES)));
                    model.setBookAuth(cursor.getString(cursor.getColumnIndexOrThrow(COL_BOOK_AUTH)));
                    model.setPrice(cursor.getString(cursor.getColumnIndexOrThrow(COL_PRICE)));
                    bookList.add(model);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
        }

        return bookList;
    }
    public Cursor getAllBorrowedBooks() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM borrowed_books", null);
    }
    public List<BookListModel> getAvailableBooks() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<BookListModel> bookList = new ArrayList<>();
        Cursor cursor = null;

        try {
            // Assuming 'BookStatus' column exists and 'Available' means book is available
            // Or use Qty > 0 to check availability
            cursor = db.rawQuery("SELECT * FROM " + TABLE_BOOKS + " WHERE Qty > 0", null);

            if (cursor.moveToFirst()) {
                do {
                    BookListModel model = new BookListModel();
                    model.setBookId(cursor.getString(cursor.getColumnIndexOrThrow(COL_BOOK_ID)));
                    model.setBookName(cursor.getString(cursor.getColumnIndexOrThrow(COL_BOOK_NAME)));
                    model.setQty(cursor.getString(cursor.getColumnIndexOrThrow(COL_QTY)));
                    model.setBookDes(cursor.getString(cursor.getColumnIndexOrThrow(COL_BOOK_DES)));
                    model.setBookAuth(cursor.getString(cursor.getColumnIndexOrThrow(COL_BOOK_AUTH)));
                    model.setPrice(cursor.getString(cursor.getColumnIndexOrThrow(COL_PRICE)));
                    model.setBookStatus(cursor.getString(cursor.getColumnIndexOrThrow("BookStatus"))); // if exists
                    bookList.add(model);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
        }

        return bookList;
    }



    // Get list of books filtered by student (assuming you have this)
    public List<BookListModel> BookListDataByStd(String studentName) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<BookListModel> bookList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(
                    "SELECT * FROM " + TABLE_BOOKS + " WHERE " + COL_BOOK_NAME + " LIKE ?",
                    new String[]{"%" + studentName + "%"});
            if (cursor.moveToFirst()) {
                do {
                    BookListModel model = new BookListModel();
                    model.setBookId(cursor.getString(cursor.getColumnIndexOrThrow(COL_BOOK_ID)));
                    model.setBookName(cursor.getString(cursor.getColumnIndexOrThrow(COL_BOOK_NAME)));
                    model.setQty(cursor.getString(cursor.getColumnIndexOrThrow(COL_QTY)));
                    model.setBookDes(cursor.getString(cursor.getColumnIndexOrThrow(COL_BOOK_DES)));
                    model.setBookAuth(cursor.getString(cursor.getColumnIndexOrThrow(COL_BOOK_AUTH)));
                    model.setPrice(cursor.getString(cursor.getColumnIndexOrThrow(COL_PRICE)));
                    bookList.add(model);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return bookList;
    }


    // Get list of all students
    public List<Studentlistmodel> StudentListData() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Studentlistmodel> studentList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);

            if (cursor.moveToFirst()) {
                do {
                    Studentlistmodel model = new Studentlistmodel();
                    model.setFirstname(cursor.getString(cursor.getColumnIndexOrThrow(COL_FIRST_NAME)));
                    model.setLastname(cursor.getString(cursor.getColumnIndexOrThrow(COL_LAST_NAME)));
                    model.setGender(cursor.getString(cursor.getColumnIndexOrThrow(COL_GENDER)));
                    model.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL_ID)));
                    model.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(COL_PASSWORD)));
                    model.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(COL_ADDRESS)));
                    studentList.add(model);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
        }

        return studentList;
    }

}

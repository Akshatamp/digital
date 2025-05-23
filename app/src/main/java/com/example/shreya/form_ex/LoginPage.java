package com.example.shreya.form_ex;

import android.content.Intent;
import android.database.Cursor;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {


    EditText editemail;
    EditText editpaswrd;
    Button btnlogin;
    TextView txtmsg, txtreg;
    DataBaseHelperClass dataBaseHelperClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        try {

            dataBaseHelperClass = new DataBaseHelperClass(this);
            editemail = (EditText) findViewById(R.id.editemail);
            editpaswrd = (EditText) findViewById(R.id.editpaswrd);
            btnlogin = (Button) findViewById(R.id.btnlogin);
            txtmsg = (TextView) findViewById(R.id.txtmsg);
            txtreg = (TextView) findViewById(R.id.txtreg);

            txtreg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                }
            });


            btnlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = editemail.getText().toString().trim();
                    String password = editpaswrd.getText().toString().trim();

                    if (name.equals("") || password.equals("")) {
                        Toast.makeText(getApplicationContext(), "Both Name and Password are required", Toast.LENGTH_LONG).show();

                    } else if (name.equals("admin") && password.equals("admin")) {
                        startActivity(new Intent(getApplicationContext(), LibrarianMenu.class));

                    } else if (!name.equals("admin") && !password.equals("admin")) {

                        Cursor cursor = dataBaseHelperClass.StudentLogin(
                                name,
                                password
                        );
                        if (cursor.getCount() > 0) {
                            startActivity(new Intent(LoginPage.this, studentmenu_activity.class));
                        } else {
                            Toast.makeText(LoginPage.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(LoginPage.this, "invalid username and password", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage() + " " + ex.getStackTrace()[0], Toast.LENGTH_SHORT).show();
        }
    }
}

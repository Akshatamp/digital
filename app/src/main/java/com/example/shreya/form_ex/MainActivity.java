package com.example.shreya.form_ex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button register;
    EditText editfname, editlname, editregno, editmobileno, editemail, editpassword, editaddress, editdept;
    RadioGroup radioGroup;
    RadioButton radioMale, radioFemale;
    DataBaseHelperClass dataBaseHelperClass;
    TextView txtSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            register = (Button) findViewById(R.id.btnRegister);
            editfname = (EditText) findViewById(R.id.editfname);
            editlname = (EditText) findViewById(R.id.editlname);
            editregno = (EditText) findViewById(R.id.editregno);
            editmobileno = (EditText) findViewById(R.id.editmobileno);
            editemail = (EditText) findViewById(R.id.editemail);
            editpassword = (EditText) findViewById(R.id.editpassword);
            editaddress = (EditText) findViewById(R.id.editaddress);
            txtSign = (TextView) findViewById(R.id.txtSign);

            editdept = (EditText) findViewById(R.id.editdept);
            dataBaseHelperClass = new DataBaseHelperClass(this);


            txtSign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), LoginPage.class));
                }
            });


            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(MainActivity.this, "You have registered", Toast.LENGTH_SHORT).show();


                    boolean isAdded = dataBaseHelperClass.StudentRegister(
                            editfname.getText().toString(),
                            editlname.getText().toString(),
                            editregno.getText().toString(),
                            editdept.getText().toString(),
                            editemail.getText().toString(),
                            editpassword.getText().toString(),
                            editaddress.getText().toString()
                    );

                    if (isAdded) {
                        dataBaseHelperClass.ToastMsg("Register Successfully");
                    } else {
                        dataBaseHelperClass.ToastMsg("Try Again");
                    }


                }
            });
        } catch (Exception ex)

        {
            //ToastMsg(ex.getMessage() + " " + ex.getStackTrace()[0]);
            getalertbox(ex.getMessage() + " " + ex.getStackTrace()[0], "Exception");
        }


    }

    public void ToastMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public boolean ChkEmpty(EditText edit1) {

        if (edit1.getText().toString().trim().length() == 0) {
            return true;
        } else {
            return false;
        }

    }


    public void getalertbox(String msg, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "you clicked OK button", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "you have clicked cancel", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}



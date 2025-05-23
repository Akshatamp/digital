package com.example.shreya.form_ex;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateProfileActivity extends AppCompatActivity {

    private EditText editFname, editLname, editRegNo, editDept, editEmail, editPassword, editMobile, editAddress;
    private RadioGroup radioGroup;
    private RadioButton radioMale, radioFemale;
    private Button btnUpdate;
    private DataBaseHelperClass databaseHelper;

    private static final String TAG = "UpdateProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        // Initialize Views
        editFname = findViewById(R.id.editfname);
        editLname = findViewById(R.id.editlname);
        radioGroup = findViewById(R.id.radioGroup);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        editRegNo = findViewById(R.id.editregno);
        editDept = findViewById(R.id.editdept);
        editEmail = findViewById(R.id.editemail);
        editPassword = findViewById(R.id.editpassword);
        editMobile = findViewById(R.id.editmobileno);
        editAddress = findViewById(R.id.editaddress);
        btnUpdate = findViewById(R.id.btnUpdate);

        databaseHelper = new DataBaseHelperClass(this);

        btnUpdate.setOnClickListener(v -> {
            Log.d(TAG, "Update button clicked");

            // Get all inputs
            String fname = editFname.getText().toString().trim();
            String lname = editLname.getText().toString().trim();

            // Check which radio button is checked
            String gender = "";
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId == R.id.radioMale) {
                gender = "Male";
            } else if (selectedId == R.id.radioFemale) {
                gender = "Female";
            }

            String regno = editRegNo.getText().toString().trim();
            String dept = editDept.getText().toString().trim();
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();
            String mobile = editMobile.getText().toString().trim();
            String address = editAddress.getText().toString().trim();

            // Validate inputs
            if (fname.isEmpty() || lname.isEmpty() || gender.isEmpty() || regno.isEmpty() || dept.isEmpty()
                    || email.isEmpty() || password.isEmpty() || mobile.isEmpty() || address.isEmpty()) {
                Toast.makeText(UpdateProfileActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Call database update function
            boolean updated = databaseHelper.updateStudentProfile(regno, fname, lname, gender, dept, email, password, mobile, address);

            if (updated) {
                Toast.makeText(UpdateProfileActivity.this, "Profile updated successfully!", Toast.LENGTH_LONG).show();
                Log.d(TAG, "Profile updated for regno: " + regno);
            } else {
                Toast.makeText(UpdateProfileActivity.this, "Update failed! Student not found.", Toast.LENGTH_LONG).show();
                Log.d(TAG, "Update failed for regno: " + regno);
            }
        });
    }
}

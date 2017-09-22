package com.example.akil.onusondhan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText ConfPass;
    private EditText PhonNum;
    private Spinner agespnr;
    private Spinner genderspnr;
    private Button buttonSignup;
    private TextView loginme;
    private Context context;
    private ProgressBar progressBar;

    //private ProgressDialog progressDialog;
    DatabaseReference databaseArtists;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        context = this;
        databaseArtists = FirebaseDatabase.getInstance().getReference("user");

        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.etName);
        editTextPassword = (EditText) findViewById(R.id.etPassword);
        ConfPass = (EditText) findViewById(R.id.confPassword);
        PhonNum = (EditText) findViewById(R.id.phoneNumber);
        buttonSignup = (Button) findViewById(R.id.bSignUp);
        agespnr = (Spinner) findViewById(R.id.agespinner);
        genderspnr = (Spinner) findViewById(R.id.genderspinner);
        loginme = (TextView) findViewById(R.id.tLoginMe);

        int TYPE = Integer.parseInt(agespnr.getSelectedItem().toString());
        //String gender = genderspnr.getSelectedItem().toString();
        //progressDialog = new ProgressDialog(this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //attaching listener to button sign up

        buttonSignup.setOnClickListener(this);
        loginme.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignup){
            String checkPass1 = editTextPassword.getText().toString().trim();
            String checkPass2 = ConfPass.getText().toString().trim();
            if(checkPass1.contentEquals(checkPass2)){
                registerUser();
            }else{
                AppConstant.showAlertMessage(context,"Password Didn't Match");
            }

        }
        if(view==loginme){
            Intent signin = new Intent(this,SignInActivity.class);
            startActivity(signin);
            finish();
        }

    }

    private void registerUser() {

        //getting all the inputs from the user
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //validation
        if (TextUtils.isEmpty(email)){
            AppConstant.showAlertMessage(context,"PLease Enter Email");
            return;
        }

        if (TextUtils.isEmpty(password)){
            AppConstant.showAlertMessage(context,"Please Enter Password");
            return;
        }

        if (password.length() <= 6){
            AppConstant.showAlertMessage(context,"Password too Small,Must be more than 6 Characters");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
//        progressDialog.setMessage("Registering, Please Wait...");
//        progressDialog.show();

        //now we can create the user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                            //progressDialog.hide();
                            adduser();
                            Intent home = new Intent(SignUpActivity.this,MainActivity.class);
                            startActivity(home);
                            finish();

                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(SignUpActivity.this, "Email already exists!", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(SignUpActivity.this, "Something went terrible wrong!", Toast.LENGTH_SHORT).show();
                           // progressDialog.hide();
                        }
                    }
                });
    }

    private void adduser() {
        String email1 = editTextEmail.getText().toString().trim();
        String password1 = editTextPassword.getText().toString().trim();
        String phone = PhonNum.getText().toString().trim();
        String gender = genderspnr.getSelectedItem().toString();
        int age = Integer.parseInt(agespnr.getSelectedItem().toString());
        String Id = PhonNum.getText().toString().trim();

        User user = new User(Id,email1,password1,phone,gender,age);

        databaseArtists.child(Id).setValue(user);
    }
}

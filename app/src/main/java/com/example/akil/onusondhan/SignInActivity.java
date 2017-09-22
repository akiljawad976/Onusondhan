package com.example.akil.onusondhan;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private Button buttonSignin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView buttonSignup;
    private TextView forgetpass;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        context = this;
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() !=null){
            finish();
            Toast.makeText(getApplicationContext(),"User Already Logged In",Toast.LENGTH_LONG).show();
        }

        editTextEmail = (EditText) findViewById(R.id.etName);
        editTextPassword = (EditText) findViewById(R.id.etPassword);
        buttonSignin = (Button) findViewById(R.id.bLogin);
        buttonSignup = (TextView) findViewById(R.id.tGetRegister);
        forgetpass = (TextView) findViewById(R.id.tForgotYourPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //progressDialog = new ProgressDialog(this);
        buttonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(signup);
            }
        });

    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String passw = editTextPassword.getText().toString().trim();
        

        if (!AppConstant.isValidEmail(email)){
            AppConstant.showAlertMessage(context,getString(R.string.alertemail));
            return;

        }

        if (TextUtils.isEmpty(passw)){
            AppConstant.showAlertMessage(context,getString(R.string.alertpass));
            return;
        }


        progressBar.setVisibility(View.VISIBLE);


//        progressDialog.setMessage("Signing in,Please Wait...");
//        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,passw).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    finish();
                    //progressDialog.hide();
                    Intent in = new Intent(SignInActivity.this,MainActivity.class);
                    startActivity(in);
                }else {
                    //Toast.makeText(getApplicationContext(), "Please enter Valid Email!", Toast.LENGTH_SHORT).show();
                    AppConstant.showAlertMessage(context,"Please enter Valid Email!");
                    //progressDialog.hide();
                }
            }
        });

    }

}

package com.example.suitcase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    private Button loginButton;
    private TextView signUpTextView;
    private EditText emailEditText, passwordEditText;
    private TextView forgotPasswordTextView;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        firebaseAuth = FirebaseAuth.getInstance();
        init();
    }

    private void init() {
        signUpTextView = findViewById(R.id.txt_signup);
        loginButton = findViewById(R.id.btn_login);
        emailEditText = findViewById(R.id.txt_login_email);
        passwordEditText = findViewById(R.id.txt_login_password);
        forgotPasswordTextView = findViewById(R.id.txt_forgot);

        forgotPasswordTextView.setOnClickListener(this::startPasswordRecoveryProcess);
        signUpTextView.setOnClickListener(this::startSignUpActivity);
        loginButton.setOnClickListener(this::login);
    }

    private void byPass(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    private void login(View view) {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            emailEditText.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            passwordEditText.requestFocus();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(login.this, "Login Sucessful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void startPasswordRecoveryProcess(View view) {
        Intent intent = new Intent(getApplicationContext(),Forgot_Password.class);
        startActivity(intent);
    }

    private void startSignUpActivity(View view) {
        Intent intent = new Intent(this, signup.class);
        startActivity(intent);
    }
}


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

public class signup extends AppCompatActivity {
    EditText Email,Password,cPassword;
    Button Signup;
    TextView login;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        findId();
    }
    public void findId(){
        Email=findViewById(R.id.signup_email);
        Password=findViewById(R.id.signup_password);
        cPassword=findViewById(R.id.signup_Cpassword);
        Signup=findViewById(R.id.signup_btn);
        login=findViewById(R.id.txt_login);
        firebaseAuth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=Email.getText().toString().trim();
                String password=Password.getText().toString().trim();
                String cpassword=cPassword.getText().toString().trim();

                if (email.isEmpty()){
                    Toast.makeText(signup.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                if (password.isEmpty()){
                    Toast.makeText(signup.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                if (password.equals(cpassword)){
                    firebaseAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Intent intent=new Intent(getApplicationContext(),login.class);
                                        startActivity(intent);
                                        Toast.makeText(signup.this, "Successful", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else {
                                        Toast.makeText(signup.this, "Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
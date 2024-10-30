package com.example.suitcase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {
    Button GetEmail;
    EditText resetEmail;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        init();
        firebaseAuth=FirebaseAuth.getInstance();
    }
    public void init(){
        GetEmail=findViewById(R.id.getEmail_btn);
        resetEmail=findViewById(R.id.forgot_email);
        GetEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=resetEmail.getText().toString().trim();
                if (email.isEmpty()){
                    Toast.makeText(Forgot_Password.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }else {
                    firebaseAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(Forgot_Password.this, "Check Email", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });
    }
}

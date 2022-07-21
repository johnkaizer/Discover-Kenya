package com.project.elimu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    Button login, signup;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        login= findViewById(R.id.btn_login);

        mAuth  = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            finish();
            return;
        }
        signup= findViewById(R.id.btn_SignUp);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }
    private void registerUser() {
        EditText etRegisterEmail = findViewById(R.id.user_txt);
        EditText etRegisterPassword = findViewById(R.id.pass_txt);
        EditText ReetRegisterPassword = findViewById(R.id.re_pass_txt);


        String email = etRegisterEmail.getText().toString();
        String password = etRegisterPassword.getText().toString();
        String re_password = ReetRegisterPassword.getText().toString();

        if ( email.isEmpty() || password.isEmpty() || re_password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User( email);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            showMainActivity();
                                            finish();
                                        }
                                    });
                        } else {
                            Toast.makeText(SignUp.this, "Authentication failed.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this,SignInActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
    private void showMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
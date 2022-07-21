package com.project.elimu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class ItemActivity extends AppCompatActivity {
    Button logout;
    private FirebaseAuth mAuth;
    ImageView schoolImage;
    TextView population, status, description, location,title ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        mAuth = FirebaseAuth.getInstance();
        logout=findViewById(R.id.button_logout);

        schoolImage= findViewById(R.id.imageView_sch);
        population= findViewById(R.id.Category_txt);
        status= findViewById(R.id.Amount_txt);
        description= findViewById(R.id.textView10);
        location= findViewById(R.id.textView9);
        title = findViewById(R.id.txt_title);

        title.setText(getIntent().getExtras().getString("title"));
        location.setText(getIntent().getExtras().getString("location"));
        status.setText(getIntent().getExtras().getString("status"));
        description.setText(getIntent().getExtras().getString("description"));
        population.setText(getIntent().getExtras().getString("population"));
        Integer ImageUrl = getIntent().getIntExtra("schoolImage",0);
        schoolImage.setImageResource(ImageUrl);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutUser();
            }
        });
    }
    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
}
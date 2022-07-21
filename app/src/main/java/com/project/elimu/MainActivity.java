package com.project.elimu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.project.elimu.Adapter.PopularSchoolAdapter;
import com.project.elimu.Model.PopularSchool;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView popularRecycler;
    PopularSchoolAdapter popularSchoolAdapter;
    ImageView notification, profile,add;
    Button logout;
    private CardView card1, card2, card3, card4;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private List<PopularSchool> mPopularSchool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        card1 = (CardView) findViewById(R.id.card1);
//        card2 = (CardView) findViewById(R.id.card2);
//        card3 = (CardView) findViewById(R.id.card3);
//        card4 = (CardView) findViewById(R.id.card4);
//
//        card1.setOnClickListener((View.OnClickListener) this);
//        card2.setOnClickListener((View.OnClickListener) this);
//        card3.setOnClickListener((View.OnClickListener) this);
//        card4.setOnClickListener((View.OnClickListener) this);


        add = findViewById(R.id.add);
        logout = findViewById(R.id.button_logout);
        popularRecycler = findViewById(R.id.popular_school);
        popularRecycler.setHasFixedSize(true);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        popularRecycler.setLayoutManager(horizontalLayoutManager);

        mPopularSchool = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("popularSchools");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    PopularSchool popularSchool = postSnapshot.getValue(PopularSchool.class);
                    mPopularSchool.add(popularSchool);
                }

                popularSchoolAdapter = new PopularSchoolAdapter(MainActivity.this, mPopularSchool);

                popularRecycler.setAdapter(popularSchoolAdapter);
//                ProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddSchools.class);
                startActivity(intent);
            }
        });
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
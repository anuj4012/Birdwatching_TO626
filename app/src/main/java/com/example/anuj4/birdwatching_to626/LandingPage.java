package com.example.anuj4.birdwatching_to626;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LandingPage extends AppCompatActivity implements View.OnClickListener {

    Button buttonAdd, buttonSearch, buttonImportance;
    EditText editTextEnterName, editTextEnterDate, editTextEnterZip;
    EditText editTextEnterImportance, editTextSearchBird;
    private FirebaseAuth mAuth;
    TextView textViewLastBird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        mAuth = FirebaseAuth.getInstance();

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonImportance = findViewById(R.id.buttonImportance);

        editTextEnterName = findViewById(R.id.editTextEnterName);
        editTextEnterDate = findViewById(R.id.editTextEnterDate);
        editTextEnterZip = findViewById(R.id.editTextEnterZip);
        //editTextEnterImportance = findViewById(R.id.editTextEnterImportance);
        editTextSearchBird = findViewById(R.id.editTextSearchBird);

        textViewLastBird = findViewById(R.id.textViewLastBird);

        buttonAdd.setOnClickListener(this);
        buttonSearch.setOnClickListener(this);
        buttonImportance.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("message");

        String user = mAuth.getCurrentUser().getUid();

        //myRef.setValue("Testing");

        if (v == buttonAdd) {

        String addBird = editTextEnterName.getText().toString();

        String addDate = editTextEnterDate.getText().toString();

        String addZip = editTextEnterZip.getText().toString();


        Bird newBird = new Bird(addBird,addZip,addDate,user, 0);

        myRef.push().setValue(newBird);


        } else if (v == buttonSearch) {

            String searchBird = editTextSearchBird.getText().toString();

            //zipcode matching
            myRef.orderByChild("zipcode").equalTo(searchBird).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String findkey = dataSnapshot.getKey();
                    Bird findbird = dataSnapshot.getValue(Bird.class);

                    textViewLastBird.setText("The Last Bird sighting is:"  + findbird.birdname + " " + "by user" + " " + findbird.user + " " +  "on" + " " +findbird.date+ " with " + findbird.importance + " importance");
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        } else if (v == buttonImportance) {

            String findzip = editTextSearchBird.getText().toString();

            myRef.orderByChild("zipcode").equalTo(findzip).limitToLast(1).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String findkey = dataSnapshot.getKey();
                    Bird findbird = dataSnapshot.getValue(Bird.class);

                    myRef.child(findkey).child("importance").setValue(findbird.importance+1);

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




        }




    }
}

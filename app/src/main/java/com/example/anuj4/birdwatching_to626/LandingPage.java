package com.example.anuj4.birdwatching_to626;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LandingPage extends AppCompatActivity implements View.OnClickListener {

    Button buttonAdd, buttonSearch, buttonImportance;
    EditText editTextEnterName, editTextEnterDate, editTextEnterZip;
    EditText editTextEnterImportance, editTextSearchBird;

    TextView textViewLastBird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);


        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonImportance = findViewById(R.id.buttonImportance);

        editTextEnterName = findViewById(R.id.editTextEnterName);
        editTextEnterDate = findViewById(R.id.editTextEnterDate);
        editTextEnterZip = findViewById(R.id.editTextEnterZip);
        editTextEnterImportance = findViewById(R.id.editTextEnterImportance);
        editTextSearchBird = findViewById(R.id.editTextSearchBird);

        textViewLastBird = findViewById(R.id.textViewLastBird);

        buttonAdd.setOnClickListener(this);
        buttonSearch.setOnClickListener(this);
        buttonImportance.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        //myRef.setValue("Testing");

        if (v == buttonAdd) {

        String addBird = editTextEnterName.getText().toString();


        String addDate = editTextEnterDate.getText().toString();
        int addDatevalue = Integer.parseInt(addDate);

        String addZip = editTextEnterZip.getText().toString();
        int addZipvalue = Integer.parseInt(addZip);

        String addImportance = editTextEnterImportance.getText().toString();
        int addImportancevalue = Integer.parseInt(addImportance);

        Bird newBird = new Bird(addBird,addDatevalue,addZipvalue,addImportancevalue);

        myRef.push().setValue(newBird);


        } else if (v == buttonSearch) {

        } else if (v == buttonImportance) {


        }




    }
}

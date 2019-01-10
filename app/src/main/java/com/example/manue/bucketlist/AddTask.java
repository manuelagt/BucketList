package com.example.manue.bucketlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class AddTask extends AppCompatActivity {

    EditText mTitle;
    EditText mDescription;
    CheckBox mCheckBox;

    static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mTitle = findViewById(R.id.add_title);
        mDescription = findViewById(R.id.add_description);

        //Initialize the local variables
        db = AppDatabase.getInstance(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_save_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTask.this, MainActivity.class);
                Task task = new Task(false, mTitle.getText().toString(),
                        mDescription.getText().toString()
                );
                intent.putExtra(MainActivity.EXTRA_REQUEST, task);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}

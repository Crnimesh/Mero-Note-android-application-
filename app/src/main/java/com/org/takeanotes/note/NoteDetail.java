package com.org.takeanotes.note;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.org.takeanotes.R;

public class NoteDetail extends AppCompatActivity {
    Intent data;//set as a global variable

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data = getIntent();//to pass the data through as a variable


        TextView content = findViewById(R.id.noteDetailsContent);
        TextView title = findViewById(R.id.noteDetailsTitle);
        content.setMovementMethod(new ScrollingMovementMethod());//to enable all the text view in scrolling way

        content.setText(data.getStringExtra("content"));
        title.setText(data.getStringExtra("title"));
        content.setBackgroundColor(getResources().getColor(data.getIntExtra("code",0),null));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//for back button of the current page
                Intent i = new Intent(view.getContext(),EditNote.class);
                i.putExtra("title",data.getStringExtra("title"));
                i.putExtra("content",data.getStringExtra("content"));
                i.putExtra("noteId",data.getStringExtra("noteId"));
                startActivity(i);
            }
        });
    }
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {*/
                /*Snackbar.make(view,"Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();*/
                /*Intent i = new Intent(view.getContext(),EditNote.class);
                i.putExtra("title",data.getStringExtra("title"));
                i.putExtra("content",data.getStringExtra("content"));
                i.putExtra("noteId",data.getStringExtra("noteId"));
                startActivity(i);*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

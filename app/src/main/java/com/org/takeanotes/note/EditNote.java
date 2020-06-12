package com.org.takeanotes.note;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.org.takeanotes.MainActivity;
import com.org.takeanotes.R;

import java.util.HashMap;
import java.util.Map;

public class EditNote extends AppCompatActivity {
    Intent data;
    EditText editNoteTitle, editNoteContent;
    FirebaseFirestore fStore;
    ProgressBar spinner;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fStore = fStore.getInstance();
        spinner = findViewById(R.id.progressBar2);
        user = FirebaseAuth.getInstance().getCurrentUser();

        data = getIntent();

        editNoteContent = findViewById(R.id.editNoteContent);
        editNoteTitle = findViewById(R.id.editNoteTitle);

        String noteTitle = data.getStringExtra("title");
        String noteContent = data.getStringExtra("content");//to pass the data from home to edit text

        editNoteTitle.setText(noteTitle);
        editNoteContent.setText(noteContent);

        FloatingActionButton fab = findViewById(R.id.saveEditedNote);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//for back button of the current page
                String nTitle = editNoteTitle.getText().toString();
                String nContent = editNoteContent.getText().toString();

                if (nTitle.isEmpty() || nContent.isEmpty()){
                    Toast.makeText(EditNote.this,"Can not save because the field is empty", Toast.LENGTH_SHORT);
                    return;
                }
                spinner.setVisibility(View.VISIBLE);
                //To save the firebase
                DocumentReference docref = fStore.collection("notes").document(user.getUid()).collection("myNotes").document(data.getStringExtra("noteId"));
                Map<String,Object> note = new HashMap<>();
                note.put("title",nTitle);
                note.put("content",nContent);

                docref.update(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditNote.this, "Congratulation!!! Note is Updated Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditNote.this,"Error Try Again", Toast.LENGTH_SHORT).show();
                        spinner.setVisibility(View.VISIBLE);
                    }
                });


            }
        });
    }
}

package com.example.multinote.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.multinote.Class.Note;
import com.example.multinote.Data.DataLocalManager;
import com.example.multinote.R;

import java.util.List;

public class EditNoteActivity extends AppCompatActivity {
    Button confirm, exit;
    EditText content;
    TextView nameNote, date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        init();
        edit();
        eventButton();
    }

    public void init() {
        date = (TextView) findViewById(R.id.editdate);
        nameNote = (TextView) findViewById(R.id.editnamenote);
        confirm = (Button) findViewById(R.id.editconfirm);
        exit = (Button) findViewById(R.id.editexit);
        content = (EditText) findViewById(R.id.editcontent);
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        String time = today.monthDay + "/" + today.month + "/" + today.year;
        date.setText(time);
    }

    public void edit() {
        Note note;
        note = (Note) getIntent().getSerializableExtra("KEY_NAME");
        nameNote.setText(note.getTitle());
        date.setText(note.getDate());
        content.setText(note.getContent());
    }

    public void save() {
        Note note = new Note(nameNote.getText().toString(), date.getText().toString(), content.getText().toString());
        Intent i = getIntent();
        List<Note> noteList = (List<Note>) i.getSerializableExtra("List");
        int position = (int) getIntent().getSerializableExtra("position");
        noteList.remove(position);
        noteList.add(position, note);
        DataLocalManager.setNoteList(noteList);
        intent();
    }
    public void intent() {
        Intent intent = new Intent(EditNoteActivity.this, MainActivity.class);
        finish();
        startActivity(intent);
    }
    public void eventButton() {
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        nameNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note;
                note = (Note) getIntent().getSerializableExtra("KEY_NAME");
                if (note.getContent().equals(content.getText().toString())  && note.getTitle().equals(nameNote.getText().toString()))  {
                    intent();
                } else {
                    exitDialog();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        intent();
    }

    public void dialog() {
        EditText editText = new EditText(this);
        int maxLength = 15;
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(editText);
        alert.setTitle("Insert Your Title");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (editText.getText().length() != 0) {
                    nameNote.setText(editText.getText().toString().toUpperCase().trim());

                }
            }
        });
        alert.show();
    }

    public void exitDialog() {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Lưu thay đổi ?");
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                save();

            }

        });
        alert.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intent();
            }
        });
        alert.show();
    }

}

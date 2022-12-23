package com.example.multinote.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.multinote.Class.Note;
import com.example.multinote.Data.DataLocalManager;
import com.example.multinote.R;

import java.util.Locale;


public class AddNoteActivity extends AppCompatActivity {

    Button confirm, exit;
    EditText content;
    TextView nameNote,date;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        init();
        dialog();
        eventButton();

    }
    public void dialog() {
        EditText editText = new EditText(this);
        int maxLength = 15;
        editText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(editText);
        alert.setTitle("Insert Your Title");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(editText.getText().length() != 0) {
                    nameNote.setText(editText.getText().toString().toUpperCase().trim());

                }
            }
        });
        alert.show();



    }
    public void init(){
        date =(TextView) findViewById(R.id.date);
        nameNote = (TextView) findViewById(R.id.namenote);
        confirm = (Button) findViewById(R.id.confirm);
        exit = (Button) findViewById(R.id.exit);
        content = (EditText) findViewById(R.id.content);
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        String time = today.monthDay + "/" + today.month + "/" + today.year;
        date.setText(time);
    }
    public void eventButton(){
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                DataLocalManager.AddNote(nameNote,content,context);
                Intent intentMain = new Intent(AddNoteActivity.this, MainActivity.class);
                startActivity(intentMain);
                finish();

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (content.length() == 0) ;
                {
                    intent();
                }


            }
        });
        nameNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentMain = new Intent(AddNoteActivity.this, MainActivity.class);
        startActivity(intentMain);
        finish();
    }
    public void intent() {
        Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
        finish();
        startActivity(intent);
    }
}
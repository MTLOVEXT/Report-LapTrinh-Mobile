package com.example.multinote.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.multinote.Class.AdapterListView;
import com.example.multinote.Data.DataLocalManager;
import com.example.multinote.Class.Note;
import com.example.multinote.R;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    AdapterListView adapter;
    ListView list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Note> noteList = DataLocalManager.getNoteList();
        // Hiển thị list
        list = findViewById(R.id.ListViewNote);
        Adapter(noteList);
        eventButton(noteList);
    }
    public void Adapter(List<Note> noteList) {
        adapter = new AdapterListView(MainActivity.this, R.layout.note, noteList);
        list.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Intent intentAdd = new Intent(MainActivity.this, AddNoteActivity.class);
                finish();
                startActivity(intentAdd);
                return true;
            case R.id.info:
                Intent intent = new Intent(MainActivity.this, InforActivity.class);

                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
    public void eventButton(List<Note> noteList) {
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DataLocalManager.removeNote(position, noteList, MainActivity.this);
                return true;
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                intent.putExtra("KEY_NAME", noteList.get(position));
                intent.putExtra("List", (Serializable) noteList);
                intent.putExtra("position", position);
                finish();
                startActivity(intent);
            }
        });
    }


}
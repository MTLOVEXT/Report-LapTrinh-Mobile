package com.example.multinote.Data;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.format.Time;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.multinote.Class.Note;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataLocalManager {
    private static final String PREF_OJECT = "PREF_OJECT";
    private static final String PREF_LIST = "PREF_LIST";
    private static DataLocalManager instance;
    private MySharedPreferences mySharedPreferences;

    public static void init(Context context) {
        instance = new DataLocalManager();
        instance.mySharedPreferences = new MySharedPreferences(context);

    }

    public static DataLocalManager getInstance() {
        if (instance == null) {
            instance = new DataLocalManager();

        }
        return instance;
    }

    public static void setNote(Note note) {
        Gson gson = new Gson();
        String Json = gson.toJson(note);
        DataLocalManager.getInstance().mySharedPreferences.putString(PREF_OJECT, Json);
    }

    public static Note getNote() {
        String Json = DataLocalManager.getInstance().mySharedPreferences.getString(PREF_OJECT);
        Gson gson = new Gson();
        Note note = gson.fromJson(Json, Note.class);
        return note;
    }

    public static void setNoteList(List<Note> noteList) {
        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(noteList).getAsJsonArray();
        String strJsonArray = jsonArray.toString();
        DataLocalManager.getInstance().mySharedPreferences.putString(PREF_LIST, strJsonArray);

    }

    public static List<Note> getNoteList() {
        String strJsonArray = DataLocalManager.getInstance().mySharedPreferences.getString(PREF_LIST);
        List<Note> noteList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(strJsonArray);
            JSONObject jsonObject;
            Note note;
            Gson gson = new Gson();
            for (int i = 0; i < strJsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                note = gson.fromJson(jsonObject.toString(), Note.class);
                noteList.add(note);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return noteList;
    }

    public static void AddNote(TextView nameNote, EditText content, Context context) {
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        List<Note> noteList = DataLocalManager.getNoteList();
        String title = nameNote.getText().toString().trim();
        String contents = content.getText().toString().trim();
        String date = today.monthDay + "/" + today.month + "/" + today.year;
        noteList.add(new Note(title, date, contents));
        DataLocalManager.setNoteList(noteList);
        CharSequence text = "Thành Công";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public static List<Note> removeNote(int position, List<Note> noteList, Activity context) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Bạn muốn xóa note");
        alert.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<Note> noteList = DataLocalManager.getNoteList();
                noteList.remove(position);
                DataLocalManager.setNoteList(noteList);
                DataLocalManager.reset(context);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, position+"", duration);
                toast.show();


            }
        });
        alert.show();
        return noteList;
    }

    public static void reset(Activity context) {
        Intent intent = context.getIntent();
        context.finish();
        context.startActivity(intent);
    }
}

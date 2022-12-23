package com.example.baitap1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    SeekBar s;
    TextView tv1, tv2, tv3, tv4, tv5;
    DialogFragment mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s = findViewById(R.id.slider);
        s.setMax(100);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);

        s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    tv1.setBackgroundColor(Color.rgb(207, 10, 10));
                    tv2.setBackgroundColor(Color.rgb(252, 231, 0));
                    tv3.setBackgroundColor(Color.rgb(234, 4, 126));
                    tv4.setBackgroundColor(Color.rgb(192, 122, 255));
                    tv5.setBackgroundColor(Color.rgb(200, 255, 212));
                } else {
                    tv1.setBackgroundColor(Color.rgb(88 + progress, 102 + progress, 56));
                    tv2.setBackgroundColor(Color.rgb(255, 153 + progress, 204 + progress));
                    tv3.setBackgroundColor(Color.rgb(155 - progress, 0, 0 + progress));
                    tv4.setBackgroundColor(Color.rgb(0 + progress + 100, 0 + progress, 204));
                    tv5.setBackgroundColor(Color.rgb(203 + progress - 100, 7 + progress, 126));
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.more_info:
                // open a dialog
                open();  // display and handle the dialog
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void open() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("HELLO , <3!!");

        // create the button which handles the Not Now action
        alertDialogBuilder.setNegativeButton("Close",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // display a toast to prove the button is handled
                Toast.makeText(MainActivity.this,"You clicked the Close button", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        // create the button which displays the MOMA website when pressed
        alertDialogBuilder.setPositiveButton("Click here!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
                startActivity(intent);
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
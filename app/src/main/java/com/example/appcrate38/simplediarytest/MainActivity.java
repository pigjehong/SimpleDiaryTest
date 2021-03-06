package com.example.appcrate38.simplediarytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker date;
    EditText edit;
    Button but;
    String fileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = (DatePicker) findViewById(R.id.date_pick);
        edit = (EditText) findViewById(R.id.edit);
        but = (Button) findViewById(R.id.but);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);

        date.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                fileName = year + (month + 1) + day + ".txt";
                String readDate=readDiary(fileName);
                edit.setText(readDate);
                but.setEnabled(true);
            }
        });
    }

    public String readDiary(String fileName){
        String diartStr=null;
        try {
            FileInputStream fIn = openFileInput(fileName);
            byte[] buf=new byte[500];
            fIn.read(buf);
            diaryStr=new String(buf).trim();
        } catch (FileNotFoundException e){
            edit.setText("일기가 존재하지 않습니다");
            but.setText("새로 저장");
        }catch(IOException e){

        }


        return diartStr;
    }
}

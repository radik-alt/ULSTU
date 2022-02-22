package com.example.gameulstu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gameulstu.DB.DbManager;
import com.example.gameulstu.DB.Model;

import java.util.Date;
import java.util.List;

public class Result extends AppCompatActivity {

    private TextView textView;
    private ListView listView;
    private DbManager dbManager;
    ArrayAdapter<Model> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        dbManager = new DbManager(this);
        listView = findViewById(R.id.list_item);

        DbManager adapter = new DbManager(this);
        adapter.open();
        if (Words.currentTime == null) {
        }
        else {
            adapter.insert(new Model(1, Words.currentTime, String.valueOf(Words.count)));

        }


        List<Model> list = adapter.getModel();

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        adapter.close();
    }

}
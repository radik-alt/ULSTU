package com.example.threeulstu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Input extends AppCompatActivity {

    private Button click;
    private EditText edx, edx2;
    private static float x1, x2;

    public static float getX1() {
        return x1;
    }

    public static float getX2() {
        return x2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        edx2 = findViewById(R.id.edx2);
        edx = findViewById(R.id.edx);
        click = findViewById(R.id.click);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = edx.getText().toString();
                String str2 = edx2.getText().toString();
                if (!str.equalsIgnoreCase("")) {
                    if (!str2.equalsIgnoreCase("")){
                        x1 = Float.parseFloat(str);
                        x2 = Float.parseFloat(str2);
                        Intent intent = new Intent(Input.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });


    }
}
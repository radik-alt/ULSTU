package com.example.gameulstu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Levels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        TextView scores = findViewById(R.id.scores);
        scores.setText("Баллы: " + Words.count);

        Button btn = findViewById(R.id.exit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void level(View view) {

        switch (view.getId()){
            case R.id.onelevel:
                Words.next = 0;
                clean();
                Intent Oneintent = new Intent(this, GameLayout.class);
                startActivity(Oneintent);
                break;

            case R.id.twolelel:
                if (Words.count >= 16) {
                    Words.next = 1;
                    clean();
                    Intent Twointent = new Intent(this, GameLayout.class);
                    startActivity(Twointent);
                } else {
                    Toast.makeText(getApplicationContext(), "У вас меньше 16 баллов!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.threelevel:
                if (Words.count >= 30) {
                    Words.next = 2;
                    clean();
                    Intent Threeintent = new Intent(this, GameLayout.class);
                    startActivity(Threeintent);
                } else {
                    Toast.makeText(getApplicationContext(), "У вас меньше 30 баллов!", Toast.LENGTH_SHORT).show();
                }
                break;

//            case R.id.fourlevel:
//                if (Words.count >= 38) {
//                    Words.next = 3;
//                    clean();
//                    Intent Fourintent = new Intent(this, GameLayout.class);
//                    startActivity(Fourintent);
//                } else {
//                    Toast.makeText(getApplicationContext(), "У вас меньше 48 баллов!", Toast.LENGTH_SHORT).show();
//                }
//                break;

            case R.id.fivelevel:
                if (Words.count >= 42) {
                    Words.next = 4;
                    clean();
                    Intent Fiveintent = new Intent(this, GameLayout.class);
                    startActivity(Fiveintent);
                } else {
                    Toast.makeText(getApplicationContext(), "У вас меньше 62 баллов!", Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }

    private void clean() {
        Words.x_cord.clear();
        Words.y_cord.clear();
        Words.y_cordObject.clear();
        Words.x_cordObject.clear();
        Words.slov.clear();
        Words.memory.clear();
    }
}
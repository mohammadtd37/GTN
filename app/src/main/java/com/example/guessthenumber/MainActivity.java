package com.example.guessthenumber;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    TextView text, time;
    EditText con;
    Button btn, button;
    int counter = 10, random;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random = rand.nextInt(100 + 1); // number 1 to 100
        find();
        buttonWork();
        random = rand.nextInt(100 + 1);
    }

    public void find() {
        text = findViewById(R.id.text);
        btn = findViewById(R.id.btn);
        time = findViewById(R.id.time);
        button = findViewById(R.id.button);
        con = findViewById(R.id.con);
    }

    public void buttonWork() {
        button.setOnClickListener(new View.OnClickListener() { // restart button
            @Override
            public void onClick(View view) {
                counter = 10;
                text.setText("your condition");
                time.setText("10 Time Play");
                Toast.makeText(MainActivity.this, "Rest The Game", Toast.LENGTH_SHORT).show();

            }
        });
        btn.setOnClickListener(new View.OnClickListener() { // run button
            @Override
            public void onClick(View view) {
                try {
                    String x = con.getText().toString();
                    int number = Integer.parseInt(x); // get integer number
                    CheckNumber(number, MainActivity.this, con);
                } catch (NumberFormatException x) {  // exepction handling  for get number
                    Toast.makeText(MainActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();

                }
            }

        });
    }



    public void CheckNumber(int number, Context context, EditText editText) {
        if (counter != 1) {
            if (number > 0 && number < 101) {       // father if
                if (number == random) {         // win part
                    random = rand.nextInt(100 + 1); // number 1 to 100
                    Toast.makeText(context, "you win the game", Toast.LENGTH_SHORT).show();
                    counter = 11;
                    text.setText("you wine the game");
                    alert();
                } else if (number > random) {           // higher part check

                    text.setText("It is higher");

                } else {             // lower part check

                    text.setText("It is lower");
                }
            } else {              // part wrong get  numbers
                Toast.makeText(context, " Wrong ? ", Toast.LENGTH_SHORT).show();
                text.setText("Wrong ?");
                if (counter == 10) {
                    counter = 11;
                } else {
                    counter += 1;
                }
            }
        } else {

            text.setText("you Lose" + random);
            counter = 11;
            random = rand.nextInt(100 + 1); // number 1 to 100

        }
        editText.setText(null);
        counter--;
        time.setText(counter + " Time Play");

    }

    public void alert() {
        AlertDialog.Builder m = new AlertDialog.Builder(MainActivity.this);
        m.setMessage("Continue The game");
        m.setCancelable(true);
        m.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        m.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finish();
            }
        });
        m.show();

    }


}
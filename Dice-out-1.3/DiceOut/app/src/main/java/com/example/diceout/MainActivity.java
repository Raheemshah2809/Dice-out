package com.example.diceout;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView rollResult;
    int score;

    Random rand;

    int die1;
    int die2;
    int die3;

    ArrayList<Integer> dice;
    ArrayList<ImageView>diceImageViews;

    TextView scoreText;
    TextView scoreRules1;
    TextView scoreRules2;
    TextView scoreRules3;
    TextView scoreRules4;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            rollDice(view);

            }
        });

        score = 0; //this shows the score
        rollResult =  (TextView) findViewById(R.id.rollResult);
        scoreText= (TextView) findViewById(R.id.scoreText);
        rand = new Random();
        dice = new ArrayList <Integer>();

        Toast.makeText(getApplicationContext(),"Dice Out By Raheem Shah",Toast.LENGTH_LONG).show();

        ImageView die1Image = (ImageView)findViewById(R.id.die1Image);
        ImageView die2Image = (ImageView)findViewById(R.id.die2Image);
        ImageView die3Image = (ImageView)findViewById(R.id.die3Image);

        diceImageViews = new ArrayList<ImageView>();
        diceImageViews.add(die1Image);
        diceImageViews.add(die2Image);
        diceImageViews.add(die3Image);


    }

    public void rollDice(View v) {
        rollResult.setText("Clicked!");
        die1 = rand.nextInt(6) + 1;
        die2 = rand.nextInt(6) + 1;
        die3 = rand.nextInt(6) + 1;


        dice.clear();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        for (int dieOfSet = 0; dieOfSet < 3; dieOfSet++) {
            String imageName = "die_" + dice.get(dieOfSet) + ".png";

            try {
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream, null);
                diceImageViews.get(dieOfSet).setImageDrawable(d);

            } catch (IOException e) {
                e.printStackTrace();

            }
        }


        String msg;
        if (die1 == die2 && die1 == die3) {

            int scoreDelta = die1 * 100;
            msg = "OOH Baby A Triple" + die1 + "! You Score" + scoreDelta + " Points!";
            score += scoreDelta;
        } else if (die1 == die2 || die1 == die3 || die2 == die3){
            msg = "OOH Baby A Double, 50 Points";
        score += 50;
    } else{
            msg ="You Didn't Score Anything Have Another Go";
        }





        rollResult.setText(msg);
        scoreText.setText("Score:" + score);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

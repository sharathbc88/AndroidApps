package com.example.KnowTheNumber;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Field to hold the roll result text
    TextView rollResult;

    // Field to hold the score
    int score;

    // Field to hold the score text
    TextView scoreText;

    // Field to hold the score text
    TextView scoreText1;

    // Field to hold the score text
    TextView scoreText2;

    // Field to hold random number generator
    Random rand;

    // Fields to hold the die values
    int die1;
    int die2;
    int die3;
    int die;

    // ArrayList to hold all three dice ImageViews
    ArrayList<ImageView> diceImageViews;

    // ArrayList to hold all three die values
    ArrayList<Integer> dice;

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

        // Set initial score
        score = 0;

        // Create greeting
        Toast.makeText(getApplicationContext(),"Welcome to Know The Number!", Toast.LENGTH_SHORT).show();

        // Link instances to widgets in the activity view
        rollResult = (TextView) findViewById(R.id.rollResult);
        scoreText = (TextView) findViewById(R.id.scoreText);
        scoreText1 = (TextView) findViewById(R.id.scoreText1);
        scoreText2 = (TextView) findViewById(R.id.scoreText2);

        // Initialize the random number generator
        rand = new Random();

        // Create ArrayList container for the dice values
        dice = new ArrayList<Integer>();

        // Access the dice ImageView widgets
        ImageView die1image = (ImageView) findViewById(R.id.die1Image);
        ImageView die2image = (ImageView) findViewById(R.id.die2Image);
        ImageView die3image = (ImageView) findViewById(R.id.die3Image);

        // Build ArrayList with dice ImageView instances
        diceImageViews = new ArrayList<ImageView>();
        diceImageViews.add(die1image);
        diceImageViews.add(die2image);
        diceImageViews.add(die3image);
    }

    public void rollDice(View v) {
        // Roll dice
        die1 = rand.nextInt(6)+1;
        die2 = rand.nextInt(6)+1;
        die3 = rand.nextInt(6)+1;

        // Set dice values into an ArrayList
        dice.clear();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        for (int dieOfSet = 0; dieOfSet < 3; dieOfSet++) {
            String imageName = "die_" + dice.get(dieOfSet) + ".png";

            try {
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream,null);
                diceImageViews.get(dieOfSet).setImageDrawable(d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Build message with the result
        String msg;

        // Run the scoring logic to determine points scored for the roll
        die = die1+die2+die3;
        if ((die)%2 == 0) {
            // Triples
            int scoreDelta = die;

            msg = "You rolled a Even number " + die;
            score += scoreDelta;
        } else {
            // Doubles
            msg = "You rolled a Odd Number " + die;

            score += die;
        }

        // Update the app to display the result message
        //rollResult.setText(msg);
        scoreText.setText(msg);

        // Check for Prime Number
        if (die == die1 || die == 2 || die ==3 || die ==5 ||die ==7 ||die ==11 || die ==13 || die==17) {
            scoreText1.setText("And "+die +" is a Prime");
            score += 10;
            msg = "+ 10 points for a Prime number";
            rollResult.setText(msg);
        }
        else {
            scoreText1.setText( "And " + die +" is a not Prime");
            msg = " Let's roll the yellow Dice to Play ";
            rollResult.setText(msg);
        }
        // Display Score
        scoreText2.setText("Score: " + score);
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

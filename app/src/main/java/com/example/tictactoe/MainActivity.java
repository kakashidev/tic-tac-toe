package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    boolean gameOn = true;
    int currentPlayer = 1;
    int[] gameState = new int[9];
    int[][] winPositions =
            {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                    {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                    {0, 4, 8}, {2, 4, 6}};
    static int counter = 0;

    public void moveMade(View view){
        ImageView img = (ImageView) view;
        int currentImage = Integer.parseInt(img.getTag().toString());
        TextView text = (TextView) findViewById(R.id.title);

        if (!gameOn) {
            gameReset(view);
        }

        else if(gameState[currentImage]==0)
        {
            counter++;
            if(counter==9)
            {
                gameOn=false;
            }

            gameState[currentImage]=currentPlayer;
            Log.d("image", Integer.toString(currentImage));
            Log.d("image array", Arrays.toString(gameState));

            if(currentPlayer==1)
            {
                currentPlayer=2;
                img.setImageResource(R.drawable.x);
            }
            else
            {
                currentPlayer=1;
                img.setImageResource(R.drawable.o);
            }
        }
        int flag=0;
        for(int[] i : winPositions)
        {
            if(gameState[i[0]] == gameState[i[1]] && gameState[i[1]] == gameState[i[2]] && gameState[i[0]] != 0)
            {
                flag=1;

                gameOn=false;
                if(gameState[i[0]]==1)
                {
                    text.setText("X Wins");
                }
                else
                {
                    text.setText("O Wins");
                }
            }
        }

        if(counter==9 && flag==0)
        {
            gameOn = false;
            text.setText("DRAW");
        }
    }


    public void gameReset(View view) {
        gameOn = true;
        currentPlayer = 1;
        counter=0;
        TextView text = (TextView) findViewById(R.id.title);
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 0;
        }
        // remove all the images from the boxes inside the grid
        ((ImageView) findViewById(R.id.space1)).setImageResource(0);
        ((ImageView) findViewById(R.id.space2)).setImageResource(0);
        ((ImageView) findViewById(R.id.space3)).setImageResource(0);
        ((ImageView) findViewById(R.id.space4)).setImageResource(0);
        ((ImageView) findViewById(R.id.space5)).setImageResource(0);
        ((ImageView) findViewById(R.id.space6)).setImageResource(0);
        ((ImageView) findViewById(R.id.space7)).setImageResource(0);
        ((ImageView) findViewById(R.id.space8)).setImageResource(0);
        ((ImageView) findViewById(R.id.space9)).setImageResource(0);

        text.setText("TIC TAC TOE");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
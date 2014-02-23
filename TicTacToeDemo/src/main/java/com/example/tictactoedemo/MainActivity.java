package com.example.tictactoedemo;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    Board b;
    int xScore = 0;
    int oScore = 0;
    TextView xText;
    TextView oText;
    Player currPlayer;
    Button[] buttons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        b = new Board();

        // Set initial scores to zero
        xText = (TextView) findViewById(R.id.xScore);
        oText = (TextView) findViewById(R.id.oScore);
        updateScore();

        // Set current player
        currPlayer = Player.X;

        // Create button array
        buttons = new Button[9];
        for(int i = 1; i < 10; i++){
            Button btn = (Button)findViewById(getResources().getIdentifier("button" + i, "id", getPackageName()));
            btn.setTag(R.string.row,(Integer) (i-1)/3);
            btn.setTag(R.string.col,(Integer) (i-1)%3);

            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Perform action on click
                    ((Button)(v)).setText(currPlayer.toString());

                    try{
                        b.makePlay((Integer) v.getTag(R.string.row), (Integer) v.getTag(R.string.col), currPlayer);
                    }catch (RuntimeException e){
                        if (b.checkWin()) {
                            // Update which player won
                            switch (currPlayer) {
                                case X:
                                    xScore++;
                                    break;
                                case O:
                                    oScore++;
                                    break;
                            }
                        }
                        updateScore();

                        // Disable all of the buttons
                        for(Button i : buttons){
                            i.setEnabled(false);
                        }
                    }

                    // switch player
                    if(currPlayer == Player.O){
                        currPlayer = Player.X;
                    }else{
                        currPlayer = Player.O;
                    }

                    v.setEnabled(false);


                }
            });
            buttons[i-1] = btn;
        }
        // Create game buttons
        Button newGame = (Button)findViewById(R.id.newGame);
        Button reset = (Button)findViewById(R.id.reset);

        // Anonymous button listener
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fixButtons();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fixButtons();
                xScore = 0;
                oScore = 0;
                updateScore();
            }
        });
    }

    private void updateScore() {
        xText.setText("Player X: " + xScore);
        oText.setText("Player O: " + oScore);
    }

    private void fixButtons() {
        b.resetBoard();
        for (Button b : buttons) {
            b.setText("");
            b.setEnabled(true);
        }
        currPlayer = Player.X;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}

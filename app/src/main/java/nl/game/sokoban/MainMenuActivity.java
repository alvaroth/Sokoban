package nl.game.sokoban;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import nl.saxion.act.playground.R;

/**
 * The main menu activity.
 *
 * @author Jeffrey van der Klij
 */
public class MainMenuActivity extends Activity {
    private ImageButton playButton;
    private ImageButton exitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Load main.xml
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.activity_main_menu);

        playButton = (ImageButton) findViewById(R.id.play_button);
        exitButton = (ImageButton) findViewById(R.id.exit_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}

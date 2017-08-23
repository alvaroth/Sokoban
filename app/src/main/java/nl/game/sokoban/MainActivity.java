package nl.game.sokoban;

import nl.saxion.act.playground.R;
import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GestureListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The main game activity.
 * 
 * @author Jeffrey van der Klij
 */
public class MainActivity extends Activity {
	private Sokoban game;
	private SokobanBoardView gameView;
	private TextView scoreLabel;
	private TextView levelLabel;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// Load main.xml
		super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		//Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		//set content view AFTER ABOVE sequence (to avoid crash)
		this.setContentView(R.layout.main);

		// Find some of the user interface elements
		gameView = (SokobanBoardView) findViewById(R.id.game);
		scoreLabel = (TextView) findViewById(R.id.scoreTextView);
		levelLabel = (TextView) findViewById(R.id.levelTextView);

		// Create the game object. This contains all data and functionality
		// belonging to the game
		game = new Sokoban(this);

		// Tell the game board view which game board to show
		GameBoard board = game.getGameBoard();
		gameView.setGameBoard(board);
		gameView.setFixedGridSize(board.getWidth(), board.getHeight());
		final GestureDetector gdt = new GestureDetector(new GestureListener(game));
		gameView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(final View view, final MotionEvent event) {
				gdt.onTouchEvent(event);
				return true;
			}
		});

		// Do something when user clicks new game
		registerNewGameButton();

		// Tell user to start the game
		Toast.makeText(getApplicationContext(), "Lets start",
				Toast.LENGTH_SHORT).show();
	}

	/**
	 * Set a new score on the score label
	 * 
	 * @param newScore  The new score.
	 */
	public void updateScoreLabel(int newScore) {
		scoreLabel.setText(getResources().getString(R.string.turns)+newScore);
	}
	/**
	 * Set a new level on the score label
	 *
	 * @param levelnumber  The new level index.
	 */
	public void updateLevelLabel(int levelnumber) {
		levelLabel.setText(getResources().getString(R.string.level)+levelnumber);
	}

	/**
	 * Returns the view on the game board.
	 */
	public SokobanBoardView getGameBoardView() {
		return gameView;
	}

	/**
	 * Install a listener to the 'New game'-button so that it starts a new
	 * game when clicked.
	 */
	private void registerNewGameButton() {
		// Find the 'New Game'-button in the activity
		final Button homeButton = (Button) findViewById(R.id.homeButton);
		final Button resetButton = (Button) findViewById(R.id.newGameButton);

		// Add a click listener to the button that calls buildLevel()
		resetButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				game.buildLevel();
			}
		});
		homeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	public void gameFinished(ArrayAdapter scoresAdapter){
		Toast.makeText(getApplicationContext(), "Well done! You completed the whole game! Thanks for playing!",
				Toast.LENGTH_SHORT).show();

		AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
		builderSingle.setIcon(R.drawable.ic_launcher);
		builderSingle.setTitle("Your scores were:");

		builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				finish();
			}
		});
		builderSingle.setAdapter(scoresAdapter, null);
		builderSingle.show();


	}

}

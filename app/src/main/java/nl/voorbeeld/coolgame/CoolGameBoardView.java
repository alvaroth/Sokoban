package nl.voorbeeld.coolgame;

import nl.saxion.act.playground.R;
import nl.saxion.act.playground.view.GameBoardView;
import nl.voorbeeld.coolgame.objects.Box;
import nl.voorbeeld.coolgame.objects.Finish;
import nl.voorbeeld.coolgame.objects.Player;
import nl.voorbeeld.coolgame.objects.Wall;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

/**
 * A view on the CoolGame game board.
 * 
 * @author Jan Stroet
 * @author Paul de Groot
 */
public class CoolGameBoardView extends GameBoardView {
	private static final String TAG = "GameView";

	/**
	 * Constructor.
	 */
	public CoolGameBoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGameView();
	}

	/**
	 * Constructor.
	 */
	public CoolGameBoardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initGameView();
	}

	/**
	 * Loads all images that will be used for the game.
	 */
	private void initGameView() {
		Log.d(TAG, "Loading all images");

		spriteCache.setContext(this.getContext());

		// Load the 'empty' floor bitmap
		spriteCache.loadTile("empty", R.drawable.cell);

		// Load the images for the GameObjects
		spriteCache.loadTile(Box.BOX_IMAGE, R.drawable.box);
		spriteCache.loadTile(Box.BOX_IMAGE_FINISH, R.drawable.box_finish);
		spriteCache.loadTile(Player.PLAYER_IMAGE_FACE_DOWN, R.drawable.character_face_down);
		spriteCache.loadTile(Player.PLAYER_IMAGE_FACE_LEFT, R.drawable.character_face_left);
		spriteCache.loadTile(Player.PLAYER_IMAGE_FACE_RIGHT, R.drawable.character_face_right);
		spriteCache.loadTile(Player.PLAYER_IMAGE_FACE_UP, R.drawable.character_face_up);
		spriteCache.loadTile(Wall.WALL_IMAGE, R.drawable.wall);
		spriteCache.loadTile(Finish.FINISH_IMAGE, R.drawable.cell_x);
	}
}

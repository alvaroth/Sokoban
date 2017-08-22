package nl.voorbeeld.sokoban;

import android.graphics.Point;
import android.widget.Toast;

import java.util.ArrayList;

import nl.saxion.act.playground.model.Game;
import nl.saxion.act.playground.model.GameBoard;
import nl.voorbeeld.sokoban.objects.Box;
import nl.voorbeeld.sokoban.objects.Level;
import nl.voorbeeld.sokoban.objects.Player;
import nl.voorbeeld.sokoban.objects.Wall;
import nl.voorbeeld.sokoban.objects.Finish;

/**
 * Awesome game for the Speelveld-project.
 *
 * @author Paul de Groot
 */
public class Sokoban extends Game {
    /**
     * Tag used for log messages
     */
    public static final String TAG = "Sokoban";

    /**
     * Reference to the main activity, so some labels can be updated.
     */
    private MainActivity activity;

    private Level currentLevel;
    private int currentLevelIndex;

    /**
     * The number of leafs eaten.
     */
    private int score;

    /**
     * levels
     */
    private Level level1;
    private Level level2;

    private ArrayList<Level> levels;


    /**
     * Constructor.
     *
     * @param activity The main activity
     */
    public Sokoban(MainActivity activity) {
        // Create a new game board and couple it to this game
        super(new SokobanGameBoard());

        // Store reference to the main activity
        // This is used to update the score label
        this.activity = activity;

        // Reset the game
        initNewGame();
    }


    /**
     * Starts a new game.
     * Resets the score and places all objects in the right place.
     */
    public void initNewGame() {

        // insatnce levels array
        levels = new ArrayList<Level>();
        // Set the score and update the label
        score = 0;
        activity.updateScoreLabel(score);


        //create level1
        level1 = new Level();
        //set the start position of the player for this level
        //set the player Object for this level
        level1.setPlayer(new Player(new Point(2, 2)));
        //add walls for this level
        level1.addWallPositions(new Point(1, 1));
        level1.addWallPositions(new Point(2, 1));
        level1.addWallPositions(new Point(1, 3));
        level1.addWallPositions(new Point(2, 3));
        level1.addWallPositions(new Point(2, 4));
        level1.addWallPositions(new Point(3, 4));
        level1.addWallPositions(new Point(2, 5));
        level1.addWallPositions(new Point(6, 1));
        level1.addWallPositions(new Point(6, 2));
        level1.addWallPositions(new Point(6, 3));
        level1.addWallPositions(new Point(6, 4));
        level1.addWallPositions(new Point(6, 5));
        //add boxes for this level
        level1.getBoxes().add(new Box(new Point(1, 6)));
        level1.getBoxes().add(new Box(new Point(3, 2)));
        level1.getBoxes().add(new Box(new Point(3, 6)));
        level1.getBoxes().add(new Box(new Point(4, 4)));
        level1.getBoxes().add(new Box(new Point(4, 3)));
        level1.getBoxes().add(new Box(new Point(4, 6)));
        level1.getBoxes().add(new Box(new Point(5, 6)));

        //add Finish spots for this level
        level1.addFinishPositions(new Finish(new Point(1, 2)));
        level1.addFinishPositions(new Finish(new Point(1, 4)));
        level1.addFinishPositions(new Finish(new Point(3, 6)));
        level1.addFinishPositions(new Finish(new Point(4, 5)));
        level1.addFinishPositions(new Finish(new Point(4, 7)));
        level1.addFinishPositions(new Finish(new Point(5, 3)));
        level1.addFinishPositions(new Finish(new Point(6, 6)));

        levels.add(level1);

        //create level1
        level2 = new Level();
        //set the start position of the player for this level
        //set the player Object for this level
        level2.setPlayer(new Player(new Point(6, 6)));
        //add walls for this level
        level2.addWallPositions(new Point(4, 1));
        level2.addWallPositions(new Point(4, 2));
        level2.addWallPositions(new Point(4, 4));
        level2.addWallPositions(new Point(4, 5));
        level2.addWallPositions(new Point(4, 6));
        level2.addWallPositions(new Point(4, 7));
        level2.addWallPositions(new Point(3, 6));
        level2.addWallPositions(new Point(3, 7));
        level2.addWallPositions(new Point(2, 6));
        level2.addWallPositions(new Point(2, 7));
        level2.addWallPositions(new Point(1, 6));
        level2.addWallPositions(new Point(1, 7));
        level2.addWallPositions(new Point(5, 7));
        level2.addWallPositions(new Point(6, 7));
        level2.addWallPositions(new Point(2, 2));
        level2.addWallPositions(new Point(2, 4));
        //add boxes for this level
        level2.getBoxes().add(new Box(new Point(5, 2)));
        level2.getBoxes().add(new Box(new Point(5, 3)));
        level2.getBoxes().add(new Box(new Point(5, 4)));

        //add Finish spots for this level
        level2.addFinishPositions(new Finish(new Point(6, 2)));
        level2.addFinishPositions(new Finish(new Point(6, 3)));
        level2.addFinishPositions(new Finish(new Point(6, 4)));

        levels.add(level2);

        //set currentlevel
        currentLevelIndex = 0;
        currentLevel = levels.get(currentLevelIndex);

        buildLevel();

    }

    public void setNextLevel() {
        Toast.makeText(activity.getApplicationContext(), "Well Done!",
                Toast.LENGTH_SHORT).show();
        currentLevelIndex++;
        currentLevel = levels.get(currentLevelIndex);
        buildLevel();
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void buildLevel() {
        GameBoard board = getGameBoard();
        board.removeAllObjects();
        for (Box box : currentLevel.getBoxes()) {
            box.setBoxFinished(false);
        }

        //set walls around the board
        for (int i = 0; i < board.getHeight(); i++) {
            board.addGameObject(new Wall(), 0, i);
            board.addGameObject(new Wall(), board.getWidth() - 1, i);
        }
        for (int i = 1; i < board.getWidth() - 1; i++) {
            board.addGameObject(new Wall(), i, 0);
            board.addGameObject(new Wall(), i, board.getHeight() - 1);
        }

        //draw the currentlevel
        board.addGameObject(currentLevel.getPlayer(), currentLevel.getPlayer().getStartPosition().x, currentLevel.getPlayer().getStartPosition().y);
        for (Point p : currentLevel.getWallPositions()) {
            board.addGameObject(new Wall(), p.x, p.y);
        }
        for (Finish f : currentLevel.getFinishes()) {
            board.addGameObject(f, f.getStartPosition().x, f.getStartPosition().y);
        }
        for (Box b : currentLevel.getBoxes()) {
            board.addGameObject(b, b.getStartPosition().x, b.getStartPosition().y);
        }
//		 Redraw the game view
        board.updateView();

    }


    /**
     * Called by Wombat if it ate a leaf. Increases the score.
     */
    public void increaseScore() {
        score++;
        activity.updateScoreLabel(score);
    }

}

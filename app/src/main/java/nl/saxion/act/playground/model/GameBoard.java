package nl.saxion.act.playground.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observable;

import android.graphics.Point;
import android.util.Log;

import nl.voorbeeld.coolgame.CoolGame;
import nl.voorbeeld.coolgame.objects.Box;
import nl.voorbeeld.coolgame.objects.Finish;
import nl.voorbeeld.coolgame.objects.Player;
import nl.voorbeeld.coolgame.objects.Wall;

/**
 * The game board, which is a rectangular array of GameObject.
 * <p>
 * You should subclass this for your own game.
 * There you will (among other things) implement
 * public void onEmptyTileClicked(int x, int y);
 * which will be called when the user clicked on a tile which had no game object
 * on it.
 *
 * @author Paul de Groot
 */
public abstract class GameBoard extends Observable {
    private static final String TAG = "Playground";

    /**
     * The game this game board is a part of.
     */
    private Game game;

    /**
     * The game instance
     */
    private CoolGame sokoban;

    /**
     * The game objects on the board.
     */
    private GameObject[][] gameBoard;


    /**
     * Create a new game board.
     *
     * @param width  The width of the board, in tiles.
     * @param height The height of the board, in tiles.
     */
    public GameBoard(int width, int height) {
        this.gameBoard = new GameObject[width][height];
    }

    /**
     * Returns the number of tiles in the X-direction of the board.
     */
    public int getWidth() {
        return gameBoard.length;
    }

    /**
     * Returns the number of tiles in the Y-direction of the board.
     */
    public int getHeight() {
        return gameBoard[0].length;
    }

    /**
     * Add a game object to the board.
     *
     * @param obj The object to place on the board.
     * @param x   The X-coordinate to place the object at.
     * @param y   The Y-coordinate to place the object at.
     * @throws IllegalArgumentException if (x,y) is not empty
     */
    public void addGameObject(GameObject obj, int x, int y) {

        if (gameBoard[x][y] != null) {
            if (gameBoard[x][y] instanceof Box) {
                System.out.println("lol");
            } else {
                throw new IllegalArgumentException("Destination already contains an object");
            }
        } else {
            gameBoard[x][y] = obj;
            obj.setPosition(x, y);
        }
    }

    /**
     * Move an object on the board.
     *
     * @param obj  The object to move.
     * @param newX The new X-coordinate of the object.
     * @param newY The new Y-coordinate of the object.
     * @throws IllegalArgumentException if (newX,newY) is not empty
     */
    public void moveObject(GameObject obj, int newX, int newY) {
        int oldX = obj.getPositionX();
        int oldY = obj.getPositionY();


        gameBoard[oldX][oldY] = null;

        //check if the next space is free
        if (gameBoard[newX][newY] != null) {
            //check if next space is a box
            if (getObject(newX, newY) instanceof Box) {
                //it is a box, check where it has to go and if that space is free OR a finish point
                if (newX > oldX) {
                    if (gameBoard[newX + 1][newY] == null) {
                        moveObject(getObject(newX, newY), newX + 1, newY);
                    } else if (gameBoard[newX + 1][newY] instanceof Finish) {
                        removeObject(gameBoard[newX + 1][newY]);
                        moveObject(getObject(newX, newY), newX + 1, newY);
                    } else {
                        throw new IllegalArgumentException("Destination already contains an object");
                    }
                } else if (newX < oldX) {
                    if (gameBoard[newX - 1][newY] == null) {
                        moveObject(getObject(newX, newY), newX - 1, newY);
                    } else if (gameBoard[newX - 1][newY] instanceof Finish) {
                        removeObject(gameBoard[newX - 1][newY]);
                        moveObject(getObject(newX, newY), newX - 1, newY);
                    } else {
                        throw new IllegalArgumentException("Destination already contains an object");
                    }
                } else if (newY > oldY) {
                    if (gameBoard[newX][newY + 1] == null) {
                        moveObject(getObject(newX, newY), newX, newY + 1);
                    } else if (gameBoard[newX][newY + 1] instanceof Finish) {
                        removeObject(gameBoard[newX][newY + 1]);
                        moveObject(getObject(newX, newY), newX, newY + 1);
                    } else {
                        throw new IllegalArgumentException("Destination already contains an object");
                    }
                } else if (newY < oldY) {
                    if (gameBoard[newX][newY - 1] == null) {
                        moveObject(getObject(newX, newY), newX, newY - 1);
                    } else if (gameBoard[newX][newY - 1] instanceof Finish) {
                        removeObject(gameBoard[newX][newY - 1]);
                        moveObject(getObject(newX, newY), newX, newY - 1);
                    } else {
                        throw new IllegalArgumentException("Destination already contains an object");
                    }
                } else {
                    //2 boxes next to eachother so cannot move (exception is caught later)
                    throw new IllegalArgumentException("Destination already contains an object");
                }
            } else if (getObject(newX, newY) instanceof Finish) {
                removeObject(gameBoard[newX][newY]);
                moveObject(obj, newX, newY);
            } else {
                //you're trying to move into a wall
                throw new IllegalArgumentException("Destination already contains an object");
            }
        }


        gameBoard[newX][newY] = obj;
        obj.setPosition(newX, newY);
        game.getGameBoard().updateView();
    }

    /**
     * Retrieves the object at the location (x, y) on the board.
     *
     * @param x The X-coordinate of the tile
     * @param y The Y-coordinate of the tile
     * @return The GameObject at (x, y) or null if there was none.
     */
    public GameObject getObject(int x, int y) {
        return gameBoard[x][y];
    }

    /**
     * Call this to notify the game board view that it should redraw.
     * You should call this any time you are done changing things on the board
     * and want to make your changes visible.
     */
    public void updateView() {
        sokoban = (CoolGame) getGame();
        for (Box box : sokoban.getCurrentLevel().getBoxes()) {
            box.setBoxFinished(checkIfBoxFinished(box));
        }

        Log.d(TAG, "Updating game view");

        setChanged();

        notifyObservers();

    }

    private boolean checkIfBoxFinished(Box box) {
        for (Point point : sokoban.getCurrentLevel().getFinishPositions()) {
            if (box.getPositionX() == point.x && box.getPositionY() == point.y) {
                return true;
            }
            if (gameBoard[point.x][point.y] == null) {
                addGameObject(new Finish(), point.x, point.y);
            }
        }
        return false;
    }

    /**
     * Remove an object from the board.
     *
     * @param object The object to remove from the board.
     */

    public void removeObject(GameObject object) {
        int x = object.getPositionX();
        int y = object.getPositionY();
        gameBoard[x][y] = null;
    }

    /**
     * Remove all objects from the game board.
     */
    public void removeAllObjects() {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                gameBoard[x][y] = null;
            }
        }
    }

    /**
     * Get a reference to the game class. You can use this to access the game
     * state.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Called if the user clicked on a tile, and no object was present on that tile.
     *
     * @param x The x-coordinate of the clicked tile
     * @param y The y-coordinate of the clicked tile
     */
    public abstract void onEmptyTileClicked(int x, int y);

    /**
     * Retrieves the background image that must be used for a specific tile.
     * Return null to use the image set using setEmptyTile().
     *
     * @param x The x-coordinate of the tile
     * @param y The x-coordinate of the tile
     * @return A image identifier to draw on this tile, or null to use the empty tile.
     */
    public String getBackgroundImg(int x, int y) {
        return null;
    }

    /**
     * Used by Game.
     */
    void setGame(Game game) {
        this.game = game;
    }


}

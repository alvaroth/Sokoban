package nl.saxion.act.playground.model;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

import nl.game.sokoban.Sokoban;
/**
 *  The gesture listener class for swipe movement
 * @author Jeffrey van der Klij
 */

public class GestureListener extends SimpleOnGestureListener {
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private Sokoban game;

    public GestureListener(Sokoban game) {
        this.game = game;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                game.getCurrentLevel().getPlayer().setFaceLeft();
                game.getGameBoard().moveObject(game.getCurrentLevel().getPlayer(), game.getCurrentLevel().getPlayer().getPositionX() - 1, game.getCurrentLevel().getPlayer().getPositionY());
                return false; // Right to left
            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                game.getCurrentLevel().getPlayer().setFaceRight();
                game.getGameBoard().moveObject(game.getCurrentLevel().getPlayer(), game.getCurrentLevel().getPlayer().getPositionX() + 1, game.getCurrentLevel().getPlayer().getPositionY());
                return false; // Left to right
            }
            if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                game.getCurrentLevel().getPlayer().setFaceUp();
                game.getGameBoard().moveObject(game.getCurrentLevel().getPlayer(), game.getCurrentLevel().getPlayer().getPositionX(), game.getCurrentLevel().getPlayer().getPositionY() - 1);
                return false; // Bottom to top
            } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                game.getCurrentLevel().getPlayer().setFaceDown();
                game.getGameBoard().moveObject(game.getCurrentLevel().getPlayer(), game.getCurrentLevel().getPlayer().getPositionX(), game.getCurrentLevel().getPlayer().getPositionY() + 1);
                return false; // Top to bottom
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        return false;

    }
}
